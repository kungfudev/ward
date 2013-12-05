package com.kungfudev.ward.interfaces.rest;

import org.geotools.data.Query;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.jts.JTS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.expression.Literal;
import org.opengis.filter.expression.PropertyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.StringWriter;

/**
 * User: Kevin W. Sewell
 * Date: 2013-12-05
 * Time: 15h11
 */
@Controller
@RequestMapping(value = "/wards/geometry")
public class WardGeometryController {

    @Autowired
    private SimpleFeatureSource wardFeatureSource;

    @Autowired
    private FilterFactory2 filterFactory;

    @ResponseBody
    @RequestMapping("/{id}")
    public String findOne(@PathVariable("id") String id) {

        Filter filter = filterFactory.equals(getWardIdProperty(), getString(id));

        return findOneByFilter(filter);
    }

    @ResponseBody
    @RequestMapping(value = "/search/findByCoordinates")
    public String findByCoordinates(@RequestParam("longitude") Double longitude,
                                    @RequestParam("latitude")  Double latitude) {

        Filter filter = filterFactory.contains(getGeomProperty(), getPoint(longitude, latitude));

        return findOneByFilter(filter);
    }

    @ResponseBody
    @RequestMapping(value = "/search/findByMunicipalityId")
    public String findByMunicipalityId(@RequestParam("municipalityId") String municipalityId) {

        Filter filter = filterFactory.equals(getMunicipalityIdProperty(), getString(municipalityId));

        return findByFilter(filter);
    }

    private String findOneByFilter(Filter filter) {

        Query query = new Query("Wards2011", filter);

        SimpleFeatureIterator simpleFeatureIterator = null;

        try {
            SimpleFeatureCollection simpleFeatureCollection = wardFeatureSource.getFeatures(query);
            simpleFeatureIterator = simpleFeatureCollection.features();
            if (simpleFeatureIterator.hasNext()) {
                SimpleFeature simpleFeature = simpleFeatureIterator.next();
                return toJson(simpleFeature);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (simpleFeatureIterator != null) {
                simpleFeatureIterator.close();
            }
        }

        return null;
    }

    private String toJson(SimpleFeature simpleFeature) {

        FeatureJSON featureJSON = new FeatureJSON();
        StringWriter writer = new StringWriter();

        try {
            featureJSON.writeFeature(simpleFeature, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return writer.toString();
    }

    private String toJson(SimpleFeatureCollection simpleFeatureCollection) {

        FeatureJSON featureJSON = new FeatureJSON();
        StringWriter writer = new StringWriter();

        try {
            featureJSON.writeFeatureCollection(simpleFeatureCollection, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return writer.toString();
    }

    private String findByFilter(Filter filter) {

        Query query = new Query("Wards2011", filter);

        try {
            SimpleFeatureCollection simpleFeatureCollection = wardFeatureSource.getFeatures(query);
            return toJson(simpleFeatureCollection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertyName getGeomProperty() {
        return filterFactory.property("the_geom");
    }

    private PropertyName getMunicipalityIdProperty() {
        return filterFactory.property("CAT_B");
    }

    private PropertyName getWardIdProperty() {
        return filterFactory.property("WARD_ID");
    }

    private Literal getPoint(double latidude, double longitude) {
        return filterFactory.literal(JTS.toGeometry(new DirectPosition2D(latidude, longitude)));
    }

    private Literal getString(String string) {
        return filterFactory.literal(string);
    }
}
