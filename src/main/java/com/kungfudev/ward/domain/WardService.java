package com.kungfudev.ward.domain;

/**
 * User: Kevin W. Sewell
 * Date: 2013-12-05
 * Time: 15h09
 */
public interface WardService {

    Ward findByCoordinates(Double longitude, Double latitude);

}
