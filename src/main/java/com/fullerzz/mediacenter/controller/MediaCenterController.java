package com.fullerzz.mediacenter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaCenterController {

    @GetMapping("/")
    public String index() {
        return "Spring Boot is running";
    }
}
