package com.ccit.service;

import com.ccit.dao.FileDAO;
import com.ccit.dao.RecordDAO;
import com.ccit.pojo.File;
import com.ccit.pojo.Record;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Named
@Transactional
public class RecordService {
    @Inject
    private RecordDAO recordDAO;
    @Inject
    private FileDAO fileDAO;
    @Value("${uppath}")
    private String path;

    public void add(Record record){
        recordDAO.saveOrUpdate(record);
    }

    public Integer saveFile(MultipartFile file) {
        String filerealname = file.getOriginalFilename();
        String contentType = file.getContentType();
        String size = FileUtils.byteCountToDisplaySize(file.getSize());
        String filename = UUID.randomUUID().toString();
        if(filerealname.lastIndexOf(".") != -1){
            filename = filename + filerealname.substring(filerealname.indexOf("."),filerealname.length());
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(path+filename);
            IOUtils.copy(file.getInputStream(),outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File imageFile = new File();
        imageFile.setFilename(filename);
        imageFile.setFilerealname(filerealname);
        imageFile.setSize(size);
        imageFile.setContenttype(contentType);

        return fileDAO.save(imageFile);

    }

    public List<Record> findByPatientId(Integer id) {
       return recordDAO.findByPatientId(id);
    }
}
