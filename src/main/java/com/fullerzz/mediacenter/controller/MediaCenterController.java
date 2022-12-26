package com.fullerzz.mediacenter.controller;

import com.fullerzz.mediacenter.service.S3RetrieverService;
import com.fullerzz.mediacenter.service.S3UploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/media/{filename}")
    public String getMedia(@PathVariable("filename") String filename) {
        return s3RetrieverService.getMedia(filename);
    }

    @GetMapping("/media/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable("filename") String filename) {
        byte[] media = s3RetrieverService.downloadMedia(filename);
        ByteArrayResource resource = new ByteArrayResource(media);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                            .filename(filename)
                            .build().toString())
                .body(resource);
    }

    @PostMapping("/upload")
    public String uploadTest() {
        return s3UploaderService.uploadFile();
    }

}
