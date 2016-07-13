package com.ccit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@Controller
public class PreviewController {
    @Value("${filepath}")
    private String filepath;
    @RequestMapping(value = "/preview/{filename}",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> priview(@PathVariable("filename")String filename) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(new File(filepath,filename));
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(inputStream));
    }
}
