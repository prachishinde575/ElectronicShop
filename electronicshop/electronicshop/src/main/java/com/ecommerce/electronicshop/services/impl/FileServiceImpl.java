package com.ecommerce.electronicshop.services.impl;

import com.ecommerce.electronicshop.exceptions.BadApiRequest;
import com.ecommerce.electronicshop.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);


    @Override
    public String uploadImage(MultipartFile file, String path) {

        String originalFilename = file.getOriginalFilename();
        logger.info("Filename:{}", originalFilename);
        String filename = UUID.randomUUID().toString();
        String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtention = filename + extention;
        String fullPathWithFileName = path + File.separator + fileNameWithExtention;

        if (extention.equalsIgnoreCase(".png") || extention.equalsIgnoreCase(".jpg") || extention.equalsIgnoreCase(".jpeg")){

            // file save
            File folder = new File(path);

            if(folder.exists())



        } else{
        throw new BadApiRequest("file with this "+ extention + "not allowes!");

        return "";
    }

    @Override
    public InputStream getReource(String path, String Name) {
        return null;
    }
}
