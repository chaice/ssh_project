package com.ccit.service;

import com.ccit.mappers.SalesFileMapper;
import com.ccit.pojo.SalesFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Named
public class SalesFileService {
    @Inject
    private SalesFileMapper salesFileMapper;
    @Value("${filepath}")
    private String filepath;

    public List<SalesFile> findBySalesId(Integer salesid) {
        return salesFileMapper.findBySalesId(salesid);
    }

    public String addFile(MultipartFile file, Integer salesid) {
        String name = file.getOriginalFilename();
        String contenttype = file.getContentType();
        String size = FileUtils.byteCountToDisplaySize(file.getSize());
        String filename = UUID.randomUUID().toString();
        if(name.lastIndexOf(".") == -1){
           filename = filename;
        }else{
            String extra = name.substring(name.lastIndexOf("."));
            filename = filename + extra;
        }
        try {
            InputStream input = file.getInputStream();
            File newfile = new File(filepath,filename);
            if(!newfile.exists()){
                newfile.createNewFile();
            }
            FileOutputStream output = new FileOutputStream(newfile);
            IOUtils.copy(input,output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SalesFile salesFile = new SalesFile();
        salesFile.setSalesid(salesid);
        salesFile.setSize(size);
        salesFile.setContenttype(contenttype);
        salesFile.setFilename(filename);
        salesFile.setName(name);
        salesFileMapper.addFile(salesFile);
        return filename;
    }

    public SalesFile findById(Integer id) {
        return salesFileMapper.findById(id);
    }
}
