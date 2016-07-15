package com.ccit.controller;

import com.ccit.pojo.Document;
import com.ccit.service.DocumentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.NotFoundException;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/document")
public class DocumentContro {
    @Value("filepath")
    private String filepath;
    @Inject
    private DocumentService documentService;
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String document(Model model, @RequestParam(required = false,defaultValue = "0")
                           Integer fid) throws NotFoundException {
        if(StringUtils.isNumeric(fid.toString())){
            model.addAttribute("documentlist",documentService.findAll(fid));
            model.addAttribute("fid",fid);
            if(fid != 0){
                model.addAttribute("pFid",documentService.download(fid).getFid());
            }
            return "document/document";
        }else{
            throw new NotFoundException("数据有误");
        }

    }
    @RequestMapping(value = "/newdir/{fid}",method = RequestMethod.POST)
    public String newDir(@PathVariable("fid")Integer fid,String dirname){
        documentService.add(dirname,fid);
        return "redirect:/document";
    }
    @RequestMapping(value = "/download/{id\\d+}",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(@PathVariable("id")Integer id) throws NotFoundException, UnsupportedEncodingException, FileNotFoundException {
        Document doc = documentService.download(id);
        if(doc == null){
            throw new NotFoundException("没有发现文件!");
        }
        File file = new File(filepath,doc.getFilename());
        if(!file.exists()){
            throw new NotFoundException("没有文件!");
        }
        String filename = new String(doc.getName().getBytes("UTF-8"),"ISO-8859-1");
        FileInputStream inputStream = new FileInputStream(file);
        return ResponseEntity
                .ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(doc.getContenttype()))
                .header("Context-Disposition","attachment;filename=\""+filename+"\"")
                .body(new InputStreamResource(inputStream));
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String upload(MultipartFile file,@RequestParam("fid")Integer fid) throws NotFoundException {
        documentService.saveFile(file,fid);
        return "redirect:/document?fid="+fid;
    }
}
