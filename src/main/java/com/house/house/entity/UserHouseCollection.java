package com.house.house.entity;

/**
 * 收藏
 */
public class UserHouseCollection {
    private int uColId;
    private int uid;
    private int hid;
    private int isCol;

    public UserHouseCollection() {
    }

    public UserHouseCollection(int uColId, int uid, int hid, int isCol) {
        this.uColId = uColId;
        this.uid = uid;
        this.hid = hid;
        this.isCol = isCol;
    }

    public int getuColId() {
        return uColId;
    }

    public void setuColId(int uColId) {
        this.uColId = uColId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getIsCol() {
        return isCol;
    }

    public void setIsCol(int isCol) {
        this.isCol = isCol;
    }
}
