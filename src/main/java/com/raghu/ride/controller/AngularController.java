package com.raghu.ride.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AngularController {
    @GetMapping({"/", "/{path:[^\\.]*}"})
    public String forward() {
        System.out.println("Request received");
        return "forward:/ride-compare/browser/index.html";
    }
}