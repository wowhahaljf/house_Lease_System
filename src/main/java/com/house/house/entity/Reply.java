package com.house.house.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class Reply {
    private int rId;
    private int cId;
    private int hId;
    private int uId;
    private String from;
    private int fromId;
    private String content;
    private String to;
    private int toId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date time;
    private int commentNum;
    private int like;
    private boolean inputShow;
    private int fromReplyId;
    public Reply() {

    }

    public int getFromReplyId() {
        return fromReplyId;
    }

    public void setFromReplyId(int fromReplyId) {
        this.fromReplyId = fromReplyId;
    }

    public Reply(int rId, int cId, int hId, int uId, String from, int fromId, String content, String to, int toId, Date time, int commentNum, int like, boolean inputShow, int fromReplyId) {
        this.rId = rId;
        this.cId = cId;
        this.hId = hId;
        this.uId = uId;
        this.from = from;
        this.fromId = fromId;
        this.content = content;
        this.to = to;
        this.toId = toId;
        this.time = time;
        this.commentNum = commentNum;
        this.like = like;
        this.inputShow = inputShow;
        this.fromReplyId = fromReplyId;
    }

    public boolean isInputShow() {
        return inputShow;
    }

    public void setInputShow(boolean inputShow) {
        this.inputShow = inputShow;
    }

    public int getrId() {
        return rId;
    }
    public void setrId(int rId) {
        this.rId = rId;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
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


}
