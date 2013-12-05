package com.kungfudev.ward.domain;

/**
 * User: Kevin W. Sewell
 * Date: 2013-12-05
 * Time: 15h39
 */
public class Province {

    private String name;

    public Province() {
    }

    public Province(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name='" + name + '\'' +
                '}';
    }
}
