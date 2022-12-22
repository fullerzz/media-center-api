package com.fullerzz.mediacenter.controller;

import com.fullerzz.mediacenter.service.S3UploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaCenterController {

    @Autowired
    S3UploaderService s3UploaderService;

    @GetMapping("/")
    public String index() {
        return "Spring Boot is running";
    }

    @PostMapping
    public String uploadTest() {
        try {
            s3UploaderService.uploadFile();
            return "That may have worked...";
        } catch (Exception ex) {
            return "That didn't work...";
        }
    }

}
