package com.example.passwordservice.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class AmazonClient {

    private AmazonS3 s3Client;

    @Value("${amazonProperties.endpointUrl}")
    private String endPoint;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretAccessKey}")
    private String secretAccessKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey, this.secretAccessKey);
         s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
         log.info("Initialized amazon client");
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multipartFile) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multipartFile.getOriginalFilename()).replace(" ", "-");
    }

    private void uploadFileToS3bucket(String fileName, File file) {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        log.info("Image added to s3 bucket successfully");
    }

    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endPoint + "/" + bucketName + "/" + fileName;
            uploadFileToS3bucket(fileName, file);
            file.delete();
            log.info("Image uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileUrl;
    }

    public void deleteFileFromS3Bucket(String fileUrl) throws Exception{
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") +  1);
        s3Client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
    }
}
