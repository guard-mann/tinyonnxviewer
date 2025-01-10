package com.test_1.services;

import com.test_1.models.ONNXFile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class ONNXService {

    public void processONNXFile(String filePath) {
        String pythonApiUrl = "http://python:5000/process_onnx";
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> request = new HashMap<>();
        request.put("file_path", filePath);

        Map<String, String> response;
        try {
          response = restTemplate.postForObject(pythonApiUrl, request, Map.class);
        } catch (Exception e) {
          System.out.println("Error communicating with Flask server: " + e.getMessage());
          e.printStackTrace();
        }
    }
}

