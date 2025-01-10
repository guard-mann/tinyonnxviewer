package com.test_1.services;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.io.IOException;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName = "XX"; // S3バケット名を指定

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    // アップロード
    public String uploadFileToS3(InputStream inputStream, String fileName) throws IOException {
       
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key("uploaded/" + fileName)
                .contentType("application/octet-stream")
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, inputStream.available()));

        // オブジェクトのURLを手動で組み立て
        return "https://" + bucketName + ".s3.amazonaws.com/uploaded/" + fileName;
    }

    // 削除
    public void deleteFileFromS3(String fileName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key("uploaded/" + fileName)  // ファイルパス指定
                .build();        
        s3Client.deleteObject(deleteObjectRequest);  // S3上のファイルを削除
    }
}

