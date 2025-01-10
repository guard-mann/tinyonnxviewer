package com.test_1.controllers;

import com.test_1.models.ONNXFile;
import com.test_1.services.ONNXService;
import com.test_1.services.S3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/api/onnx")
public class ONNXController {

    @Autowired
    private ONNXService onnxService;
    
    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public void uploadONNXFile(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        String s3Url = s3Service.uploadFileToS3(file.getInputStream(), file.getOriginalFilename());
        onnxService.processONNXFile(s3Url);        
        TimeUnit.SECONDS.sleep(10);
        s3Service.deleteFileFromS3(file.getOriginalFilename());
    }
}

