package com.kungfudev.ward.domain;

import java.util.List;

/**
 * User: Kevin W. Sewell
 * Date: 2013-12-05
 * Time: 15h09
 */
public interface WardService {

    Ward findOne(String id);

    List<Ward> findAll();

    List<Ward> findByProvinceName(String provinceName);

    List<Ward> findByMunicipalityId(String municipalityId);

    Ward findByCoordinates(Double longitude, Double latitude);
}
