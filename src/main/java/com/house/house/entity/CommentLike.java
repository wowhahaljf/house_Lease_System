package com.house.house.entity;

public class CommentLike {
    private int clId;
    private int cId;
    private int uId;
    private int likeStatus;

    public CommentLike(int cId, int uId) {
        this.cId = cId;
        this.uId = uId;
    }

    public CommentLike() {
    }

    public CommentLike(int clId, int cId, int uId) {
        this.clId = clId;
        this.cId = cId;
        this.uId = uId;
    }

    public int getClId() {
        return clId;
    }

    public void setClId(int clId) {
        this.clId = clId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }
}
