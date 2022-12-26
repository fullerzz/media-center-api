package com.fullerzz.mediacenter.controller;

import com.fullerzz.mediacenter.service.S3RetrieverService;
import com.fullerzz.mediacenter.service.S3UploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaCenterController {

    @Autowired
    private S3UploaderService s3UploaderService;

    @Autowired
    private S3RetrieverService s3RetrieverService;

    @GetMapping("/")
    public String index() {
        return "Spring Boot is running";
    }

    @GetMapping("/media")
    public String getAllMedia() {
        return s3RetrieverService.getAllMedia();
    }

    @PostMapping("/upload")
    public String uploadTest() {
        return s3UploaderService.uploadFile();
    }

}
