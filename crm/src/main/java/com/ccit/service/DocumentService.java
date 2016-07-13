package com.ccit.service;

import com.ccit.mappers.DocumentMapper;
import com.ccit.pojo.Document;
import com.ccit.utils.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
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
public class DocumentService {
    @Inject
    private DocumentMapper documentMapper;
    @Value("${filepath}")
    private String filepath;


    public List<Document> findAll(Integer fid){
        return documentMapper.findAll(fid);
    }
    public void add(String dirname,Integer fid) {
      Document doc = new Document();
      doc.setName(dirname);
      doc.setFid(fid);
      doc.setType("dir");
      doc.setCreatuser(ShiroUtil.getPrincipal().getName());
      documentMapper.insert(doc);
    }
    @Transactional
    public void saveFile(MultipartFile file,Integer fid) throws IOException {
        InputStream input = file.getInputStream();
        String name = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String filename = "";
        if(name.substring(name.lastIndexOf(".")) == "-1"){
            filename = uuid;
        }else{
            filename = uuid + name.substring(name.lastIndexOf("."));
        }
        FileOutputStream output = new FileOutputStream(new File(filepath,filename));
        IOUtils.copy(input,output);
        String contenttype = file.getContentType();
        String size = FileUtils.byteCountToDisplaySize(file.getSize());
        Document doc = new Document();
        doc.setName(name);
        doc.setSize(size);
        doc.setType("do");
        doc.setFilename(filename);
        doc.setFid(fid);
        doc.setContenttype(contenttype);
        doc.setCreatuser(ShiroUtil.getPrincipal().getName());
        documentMapper.insert(doc);
    }

    public Document download(Integer id) {
         return documentMapper.findById(id);
    }
}
