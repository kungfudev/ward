package com.kungfudev.ward.interfaces.rest;

import com.kungfudev.ward.domain.Ward;
import com.kungfudev.ward.domain.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: Kevin W. Sewell
 * Date: 2013-12-05
 * Time: 15h11
 */
@Controller
@RequestMapping(value = "/wards")
public class WardServiceController {

    @Autowired
    private WardService wardService;

    @ResponseBody
    @RequestMapping()
    public List<Ward> findAll() {

        List<Ward> wards = wardService.findAll();

        Collections.sort(wards, new Comparator<Ward>() {
            @Override
            public int compare(Ward ward1, Ward ward2) {
                return ward1.getId().compareTo(ward2.getId());
            }
        });

        return wards;
    }

    @ResponseBody
    @RequestMapping()
    public Ward findOne(String id) {
        return wardService.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/search/findByCoordinates")
    public Ward findByCoordinates(@RequestParam("longitude") Double longitude,
                                  @RequestParam("latitude")  Double latitude) {

        return wardService.findByCoordinates(longitude, latitude);
    }
}
