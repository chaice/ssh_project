package com.ccit.dbutils;


import com.ccit.dao.FileDAO;
import com.ccit.entity.FileUp;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.Part;
import java.io.*;
import java.util.List;
import java.util.UUID;

public class FileUtil {
    private FileDAO fileDAO = new FileDAO();
    public void addFile(InputStream inputStream,String headname,long count) throws IOException {
        String realname = Strings.getFileName(headname);
        String extra = realname.substring(realname.indexOf("."));
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + extra;
        String size = FileUtils.byteCountToDisplaySize(count);
        String md5 = DigestUtils.md5Hex(inputStream);
        FileUp fileUp = new FileUp();
        fileUp.setFilename(filename);
        fileUp.setRealname(realname);
        fileUp.setSize(size);
        fileUp.setMd5(md5);
        fileDAO.insert(fileUp);
    }
    public void saveFile(InputStream inputStream,String headname) throws IOException {
        String md5 =DigestUtils.md5Hex(inputStream);
        List<FileUp> list = fileDAO.findByMd5(md5);
        String realname = Strings.getFileName(headname);
        String extra = realname.substring(realname.indexOf("."));
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + extra;
        File file = new File("d:/upload/",filename);
        FileOutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream,outputStream);
            inputStream.close();
            outputStream.flush();
            outputStream.close();



    }
}
