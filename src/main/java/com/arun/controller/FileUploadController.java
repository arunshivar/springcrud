package com.arun.controller;

import com.arun.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping(value = "/file")
public class FileUploadController {

    @Value("${file.upload.path}")
    String path;

    @RequestMapping(value = "/")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if(file.isEmpty()){
            throw new StorageException("File cannot be empty");
        }

        try{
            Files.copy(file.getInputStream(),
                    Paths.get(path + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);

            return "File Uploaded Successfully!";
        }
        catch (IOException ie){
            throw new StorageException("Failed to upload file"+file.getOriginalFilename(), ie);
        }
    }

}
