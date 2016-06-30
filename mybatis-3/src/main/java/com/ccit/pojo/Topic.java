package com.ccit.pojo;


public class Topic {
    private Integer id;
    private String title;
    private String text;
    private String createtime;
    private Integer userid;
    private Integer nodeid;
    private Integer viewnum;
    private Integer favnum;
    private Integer likenum;
    private Integer replynum;
    private Integer replytime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    public Integer getViewnum() {
        return viewnum;
    }

    public void setViewnum(Integer viewnum) {
        this.viewnum = viewnum;
    }

    public Integer getFavnum() {
        return favnum;
    }

    public void setFavnum(Integer favnum) {
        this.favnum = favnum;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }

    public Integer getReplytime() {
        return replytime;
    }

    public void setReplytime(Integer replytime) {
        this.replytime = replytime;
    }

    public Topic(String title, String createtime) {
        this.title = title;
        this.createtime = createtime;
    }

    public Topic() {
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", createtime='" + createtime + '\'' +
                ", userid=" + userid +
                ", nodeid=" + nodeid +
                ", viewnum=" + viewnum +
                ", favnum=" + favnum +
                ", likenum=" + likenum +
                ", replynum=" + replynum +
                ", replytime=" + replytime +
                '}';
    }
}
