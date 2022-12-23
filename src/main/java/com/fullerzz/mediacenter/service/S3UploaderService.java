package com.fullerzz.mediacenter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Paths;

@Service
@Slf4j
public class S3UploaderService {

    private static final String S3_BUCKET_NAME = "fullerzz-media";

    public String uploadFile() {
        Region region = Region.US_WEST_1;
        S3Client s3 = S3Client.builder().region(region).build();
        log.info("S3 Client created");

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(S3_BUCKET_NAME)
                .key("test1.txt")
                .build();
        log.info("Put Object Request built");
        log.info("Attempting to upload file to S3 bucket");
        try {
            s3.putObject(objectRequest, Paths.get("src/main/java/com/fullerzz/mediacenter/service/test.txt"));
            log.info("File uploaded successfully to S3");
            return "File uploaded successfully to S3 bucket";
        } catch (Exception ex) {
            log.error("File not uploaded to S3", ex);
            return ex.getLocalizedMessage() + "\nFailed to upload file to S3 bucket";
        }
    }
}
