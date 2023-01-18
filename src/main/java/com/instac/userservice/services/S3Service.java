package com.instac.userservice.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;

@Service
public class S3Service {

    private AmazonS3 amazonS3;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Autowired
    public S3Service (AmazonS3 amazonS3){
        this.amazonS3 = amazonS3;
    }

    public String saveImage(String fileName, InputStream is){
        String nowDate = LocalDateTime.now().toString().replace(".", "-");
        String fileSavedName = nowDate+fileName;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        amazonS3.putObject(bucketName, fileSavedName, is, objectMetadata);
        return fileSavedName;
    }
}
