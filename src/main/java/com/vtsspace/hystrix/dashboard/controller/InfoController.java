package com.vtsspace.hystrix.dashboard.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class InfoController {

    @HystrixCommand(fallbackMethod = "fallBackInfo", commandKey = "info", groupKey = "info")
    @GetMapping("/info")
    public String info() {
        if (RandomUtils.nextInt() % 5 == 0) {
            throw new RuntimeException("Failed!");
        }
        return "My name is Tien Nguyen from VTS Space";
    }

    @HystrixCommand(fallbackMethod = "fallBackCompany", commandKey = "company", groupKey = "company")
    @GetMapping("/company")
    public String company() {
        if (RandomUtils.nextInt() % 3 == 0) {
            throw new RuntimeException("Failed!");
        }
        return "VTS Space";
    }

    public String fallBackInfo() {
        return "Fall Back from Info";
    }

    public String fallBackCompany() {
        return "Fall Back from Company";
    }
}
