package com.fullerzz.mediacenter.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Paths;

@Service
public class S3UploaderService {

    private static final String S3_BUCKET_NAME = "fullerzz-media";

    public void uploadFile() {
        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder().region(region).build();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(S3_BUCKET_NAME)
                .key("test1.txt")
                .build();

        s3.putObject(objectRequest, Paths.get("test.txt"));
    }
}
