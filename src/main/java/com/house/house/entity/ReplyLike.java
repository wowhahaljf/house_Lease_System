package com.house.house.entity;

public class ReplyLike {
    private int rlId;
    private int uId;
    private int rId;
    private int likeStatus;

    public ReplyLike() {
    }

    public ReplyLike(int rlId, int uId, int rId, int likeStatus) {
        this.rlId = rlId;
        this.uId = uId;
        this.rId = rId;
        this.likeStatus = likeStatus;
    }

    public int getRlId() {
        return rlId;
    }

    public void setRlId(int rlId) {
        this.rlId = rlId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }
}
