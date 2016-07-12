package com.ccit.test;

import com.ccit.dao.FileDAO;
import com.ccit.dbutils.Strings;
import com.ccit.entity.FileUp;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class TestFileDAO {
    FileDAO fileDAO = new FileDAO();
    Logger logger = LoggerFactory.getLogger(TestFileDAO.class);
    @Test
    public void testInsert(){
        FileUp file = new FileUp();
        String realname = Strings.getFileName("form-data; name=\"file\"; filename=\"1166283114bc815e34o.jpg\"");
        String extra = realname.substring(realname.indexOf("."));
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + extra;
        file.setRealname(realname);
        file.setFilename(filename);
        fileDAO.insert(file);
    }
    @Test
    public void testFindById(){
        FileUp file = fileDAO.findById(5);
        logger.debug("size:{}", FileUtils.byteCountToDisplaySize(102761));
        logger.debug("file:{}",file);
    }
    @Test
    public void testFindByMd5(){
       List<FileUp> list= fileDAO.findByMd5("e43f88babcf09a7a440ec3414038b7de");
        Assert.assertNotNull(list);
    }
}
