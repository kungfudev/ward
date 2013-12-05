package com.kungfudev.ward.domain;

import java.io.Serializable;

/**
 * User: Kevin W. Sewell
 * Date: 2013-12-05
 * Time: 15h39
 */
public final class Municipality implements Serializable {

    private String id;

    private String name;

    public Municipality() {
    }

    public Municipality(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Municipality{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
