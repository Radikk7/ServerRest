package com.example.serverrest.controller;


import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ImageController {
    @Value("${upload.path}")
    private String pathFile;
    @PostMapping("/addpicture")
    public ResponseEntity<String> addPicture(@RequestParam(name = "file")MultipartFile multipartFile) throws IOException {
            if (multipartFile!=null){
               String filename= multipartFile.getOriginalFilename();
               String a=  RandomString.make()+ filename ;
                  String b =  pathFile + "/" + a;
                  File file = new File(b);
                  multipartFile.transferTo(file);
                  return new ResponseEntity<String>(a,HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
