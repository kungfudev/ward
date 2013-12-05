package com.kungfudev.ward.domain;

import java.io.Serializable;

/**
 * User: Kevin W. Sewell
 * Date: 2013-12-05
 * Time: 15h14
 */
public final class Ward implements Serializable {

    private String id;

    private Integer number;

    private Double area;

    private Integer population;

    private Province province;

    private Municipality municipality;

    public Ward() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", area=" + area +
                ", population=" + population +
                ", province=" + province +
                ", municipality=" + municipality +
                '}';
    }
}
