package com.kungfudev.ward.domain;

import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.jts.JTS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.expression.Literal;
import org.opengis.filter.expression.PropertyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    public Ward findByCoordinates(Double longitude, Double latitude) {

        Filter filter = filterFactory.contains(getGeomProperty(), getPoint(longitude, latitude));

        Query query = new Query("Wards2011", filter);

        SimpleFeatureIterator simpleFeatureIterator = null;

        try {
            SimpleFeatureCollection simpleFeatureCollection = wardFeatureSource.getFeatures(query);
            simpleFeatureIterator = simpleFeatureCollection.features();
            if(simpleFeatureIterator.hasNext()) {

                SimpleFeature simpleFeature = simpleFeatureIterator.next();

                Ward ward = new Ward();
                ward.setProvince(new Province((String)simpleFeature.getAttribute("PROVINCE")));
                ward.setMunicipality(new Municipality((String)simpleFeature.getAttribute("CAT_B"), (String)simpleFeature.getAttribute("MUNICNAME")));
                ward.setId((String)simpleFeature.getAttribute("WARD_ID"));
                ward.setNumber((Integer)simpleFeature.getAttribute("WARDNO"));
                ward.setArea((Double)simpleFeature.getAttribute("Area"));
                ward.setPopulation((Integer)simpleFeature.getAttribute("WARD_POP"));

                return ward;
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

    private PropertyName getGeomProperty() {
        return filterFactory.property("the_geom");
    }

    private Literal getPoint(double latidude, double longitude) {
        return filterFactory.literal(JTS.toGeometry(new DirectPosition2D(latidude, longitude)));
    }

}
