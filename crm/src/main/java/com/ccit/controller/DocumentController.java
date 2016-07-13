package com.ccit.controller;

import com.ccit.pojo.Document;
import com.ccit.service.DocumentService;
import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.*;

@Controller
@RequestMapping("/document")
public class DocumentController {
    Logger logger = LoggerFactory.getLogger(DocumentController.class);
    @Inject
    private DocumentService documentService;
    @Value("${filepath}")
    private String filepath;
   @RequestMapping(value = "",method = RequestMethod.GET)
    public String doucument(Model model, @RequestParam(required = false,defaultValue = "0")Integer fid){
       model.addAttribute("documentList",documentService.findAll(fid));
       model.addAttribute("fid",fid);
       if(fid != 0){
           model.addAttribute("pFid",documentService.download(fid).getFid());
       }
        return "document/documentlist";
    }
    @RequestMapping(value = "/newdir",method = RequestMethod.POST)
    public String newDir(String dirname,Integer fid){
        documentService.add(dirname,fid);
        return "redirect:/document?fid="+fid;
    }
    @RequestMapping(value = "/savefile",method = RequestMethod.POST)
    public String loadFile(MultipartFile file,Integer fid) throws IOException {
        documentService.saveFile(file,fid);
        return "redirect:/document?fid="+fid;
    }
    @RequestMapping(value = "/download/{id}",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(@PathVariable("id")Integer id) throws UnsupportedEncodingException, NotFoundException, FileNotFoundException {
        Document document = documentService.download(id);
        if(document == null){
            throw new NotFoundException("没有发现文件!");
        }
        File file = new File(filepath,document.getFilename());
        FileInputStream inputStream = new FileInputStream(file);
        String filename = new String(document.getName().getBytes("UTF-8"),"ISO-8859-1");
       return ResponseEntity
                .ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(document.getContenttype()))
                .header("Content-Disposition","attachment;filename=\""+filename+"\"")
                .body(new InputStreamResource(inputStream));
    }
}
