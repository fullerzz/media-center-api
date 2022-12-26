package com.fullerzz.mediacenter.service;

import com.fullerzz.mediacenter.model.Media;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class S3RetrieverService {

    private List<Media> contentList = new ArrayList<>();
    private static final String S3_BUCKET_NAME = "fullerzz-media";
    private static final Region region = Region.US_WEST_1;
    private S3Client s3;

    public S3RetrieverService() {
        s3 = S3Client.builder().region(region).build();
    }

    public String getAllMedia() {
        JsonArray jsonArray = new JsonArray();

        try {
            ListObjectsV2Request listObjectsRequest = ListObjectsV2Request
                    .builder()
                    .bucket(S3_BUCKET_NAME)
                    .build();

            ListObjectsV2Response response = s3.listObjectsV2(listObjectsRequest);
            List<S3Object> objects = response.contents();

            for (S3Object obj : objects) {
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("key", obj.key());
                jsonObj.addProperty("size", obj.size());
                jsonArray.add(jsonObj);
            }

        } catch (S3Exception ex) {
            System.err.println(ex.awsErrorDetails().errorMessage());
        } catch (JsonIOException ex) {
            System.err.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }

        return jsonArray.toString();
    }

}
