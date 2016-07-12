package com.ccit.entity;


public class FileUp {
    private Integer id;
    private String filename;
    private String realname;
    private String md5;
    private String size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isprivew(){
        if(getFilename() == null){
            return false;
        }
        if(getFilename().endsWith(".jpg")||getFilename().endsWith(".png")||getFilename().endsWith(".gif")
                ||getFilename().endsWith("jpeg")||getFilename().endsWith(".pdf")){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "FileUp{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", realname='" + realname + '\'' +
                ", md5='" + md5 + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
