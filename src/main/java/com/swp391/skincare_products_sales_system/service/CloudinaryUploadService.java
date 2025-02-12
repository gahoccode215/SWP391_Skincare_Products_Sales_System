package com.swp391.skincare_products_sales_system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryUploadService {
    String uploadImage(MultipartFile file) throws IOException;
}
