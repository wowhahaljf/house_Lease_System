package com.house.house.entity;

public class Complaint {
    private int cplId;
    private int uid;
    private int hid;
    private Integer aid;
    private String cplContent;
    private int status;

    public Complaint() {
    }

    public Complaint(int uid, int hid, String cplContent) {
        this.uid = uid;
        this.hid = hid;
        this.cplContent = cplContent;
    }

    public int getCplId() {
        return cplId;
    }

    public void setCplId(int cplId) {
        this.cplId = cplId;
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

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getCplContent() {
        return cplContent;
    }

    public void setCplContent(String cplContent) {
        this.cplContent = cplContent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
