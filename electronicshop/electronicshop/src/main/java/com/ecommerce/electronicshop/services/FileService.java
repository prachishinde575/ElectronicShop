package com.ecommerce.electronicshop.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;

public interface FileService {


    public String uploadImage(MultipartFile file , String Path);

    InputStream getReource(String Path, String Name);




















}
