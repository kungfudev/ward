package com.kungfudev.ward.interfaces.rest;

import com.kungfudev.ward.domain.Ward;
import com.kungfudev.ward.domain.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "/search/findByCoordinates")
    public Ward findByCoordinates(@RequestParam("longitude") Double longitude,
                                  @RequestParam("latitude")  Double latitude) {
        return wardService.findByCoordinates(longitude, latitude);
    }
}
