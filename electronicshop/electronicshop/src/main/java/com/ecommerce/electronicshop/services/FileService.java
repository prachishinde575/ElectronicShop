package com.ecommerce.electronicshop.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface FileService {


    public String uploadFile(MultipartFile file , String path) throws IOException;

    InputStream getResource(String path, String name) throws FileNotFoundException;

}
