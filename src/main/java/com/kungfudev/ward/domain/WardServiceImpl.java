package com.kungfudev.ward.domain;

import org.geotools.data.Query;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.jts.JTS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.expression.Literal;
import org.opengis.filter.expression.PropertyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kevin W. Sewell
 * Date: 2013-12-05
 * Time: 15h09
 */
@Service
public class WardServiceImpl implements WardService {

    @Autowired
    private SimpleFeatureSource wardFeatureSource;

    @Autowired
    private FilterFactory2 filterFactory;

    @Override
    public Ward findOne(String id) {

        Filter filter = filterFactory.equals(getWardIdProperty(), getString(id));

        return findOneByFilter(filter);
    }

    @Override
    public List<Ward> findAll() {

        SimpleFeatureIterator simpleFeatureIterator = null;
        try {

            SimpleFeatureCollection simpleFeatureCollection = wardFeatureSource.getFeatures();
            simpleFeatureIterator = simpleFeatureCollection.features();

            List<Ward> wards = new ArrayList<>();
            while(simpleFeatureIterator.hasNext()) {
                SimpleFeature simpleFeature = simpleFeatureIterator.next();
                wards.add(getWard(simpleFeature));
            }

            return wards;

        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            if (simpleFeatureIterator != null) {
                simpleFeatureIterator.close();
            }
        }
    }

    @Override
    public Ward findByCoordinates(Double longitude, Double latitude) {

        Filter filter = filterFactory.contains(getGeomProperty(), getPoint(longitude, latitude));

        return findOneByFilter(filter);
    }

    private Ward findOneByFilter(Filter filter) {

        Query query = new Query("Wards2011", filter);

        SimpleFeatureIterator simpleFeatureIterator = null;

        try {
            SimpleFeatureCollection simpleFeatureCollection = wardFeatureSource.getFeatures(query);
            simpleFeatureIterator = simpleFeatureCollection.features();
            if(simpleFeatureIterator.hasNext()) {
                SimpleFeature simpleFeature = simpleFeatureIterator.next();
                return getWard(simpleFeature);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            if (simpleFeatureIterator != null) {
                simpleFeatureIterator.close();
            }
        }

        return null;
    }

    private Ward getWard(SimpleFeature simpleFeature) {

        Ward ward = new Ward();

        ward.setId((String)simpleFeature.getAttribute("WARD_ID"));
        ward.setNumber((Integer)simpleFeature.getAttribute("WARDNO"));
        ward.setArea((Double)simpleFeature.getAttribute("Area"));
        ward.setPopulation((Integer)simpleFeature.getAttribute("WARD_POP"));
        ward.setProvince(new Province((String) simpleFeature.getAttribute("PROVINCE")));
        ward.setMunicipality(new Municipality(
                (String) simpleFeature.getAttribute("CAT_B"),
                (String) simpleFeature.getAttribute("MUNICNAME"))
        );

        return ward;
    }

    private PropertyName getGeomProperty() {
        return filterFactory.property("the_geom");
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
