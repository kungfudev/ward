package com.kungfudev.ward.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: Kevin W. Sewell
 * Date: 2013-12-05
 * Time: 15h17
 */
@ContextConfiguration({
        "classpath:/META-INF/spring/spring-context.xml",
        "classpath:/META-INF/spring/spring-geotools.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class WardServiceIntegrationTest {

    @Autowired
    private WardService wardService;

    @Test
    public void shouldFindByGpsCoordinates() throws Exception {

        System.out.println(wardService.findByCoordinates(18.418207, -33.929403));
        System.out.println(wardService.findByCoordinates(18.837233, -33.958596));
    }
}
