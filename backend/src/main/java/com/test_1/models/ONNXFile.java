// models/ONNXFile.java
package com.test_1.models;

public class ONNXFile {
    private String fileName;
    private String imagePath;

    public ONNXFile(String fileName, String imagePath) {
        this.fileName = fileName;
        this.imagePath = imagePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getImagePath() {
        return imagePath;
    }
}

