package com.fullerzz.mediacenter.service;

import com.fullerzz.mediacenter.model.FileType;
import com.fullerzz.mediacenter.model.Media;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3RetrieverService {

    private List<Media> contentList = new ArrayList<>(); //TODO: Change to HashMap
    private static final String S3_BUCKET_NAME = "fullerzz-media";
    private static final Region region = Region.US_WEST_1;
    private S3Client s3;

    public S3RetrieverService() {
        s3 = S3Client.builder().region(region).build();
    }

    public String getAllMedia() {
        refreshContent();
        JsonArray jsonArray = new JsonArray();

        for (Media content : contentList) {
            JsonObject jsonObj = new JsonObject();
            jsonObj.addProperty("key", content.getFilename());
            jsonObj.addProperty("size", content.getFileSize());
            jsonArray.add(jsonObj);
        }

        return jsonArray.toString();
    }

    public String getMedia(String filename) {
        refreshContent();

        for (Media content : contentList) {
            if (filename.equals(content.getFilename())) {
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("key", content.getFilename());
                jsonObj.addProperty("size", content.getFileSize());
                return jsonObj.toString();
            }
        }

        return null;
    }

    public byte[] downloadMedia(String filename) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(S3_BUCKET_NAME)
                .key(filename)
                .build();
        ResponseInputStream<GetObjectResponse> response = s3.getObject(getObjectRequest);
        try {
            return response.readAllBytes();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public void refreshContent() {
        contentList.clear();

        try {
            ListObjectsV2Request listObjectsRequest = ListObjectsV2Request
                    .builder()
                    .bucket(S3_BUCKET_NAME)
                    .build();

            ListObjectsV2Response response = s3.listObjectsV2(listObjectsRequest);
            List<S3Object> objects = response.contents();

            for (S3Object obj : objects) {
                contentList.add(new FileType(obj.key(), obj.size()));
            }

        } catch (S3Exception ex) {
            System.err.println(ex.awsErrorDetails().errorMessage());
        }
    }

}
