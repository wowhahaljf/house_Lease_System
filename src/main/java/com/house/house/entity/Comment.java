package com.house.house.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

public class Comment {
    private int cId;
    private int hId;
    private int uId;
    private String uNickName;
    private int parentId;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date time;
    private int commentNum;
    private int like;
    private boolean inputShow;

    public boolean isInputShow() {
        return inputShow;
    }

    public void setInputShow(boolean inputShow) {
        this.inputShow = inputShow;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuNickName() {
        return uNickName;
    }

    public void setuNickName(String uNickName) {
        this.uNickName = uNickName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public Comment(int cId) {
        this.cId = cId;
    }

    public Comment() {
    }

    public Comment(int cId, int hId, int uId, String uNickName, int parentId, String content, Date time, int commentNum, int like) {
        this.cId = cId;
        this.hId = hId;
        this.uId = uId;
        this.uNickName = uNickName;
        this.parentId = parentId;
        this.content = content;
        this.time = time;
        this.commentNum = commentNum;
        this.like = like;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cId=" + cId +
                ", hId=" + hId +
                ", uId=" + uId +
                ", uNickName='" + uNickName + '\'' +
                ", parentId=" + parentId +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", commentNum=" + commentNum +
                ", like=" + like +
                ", inputShow=" + inputShow +
                '}';
    }
}
