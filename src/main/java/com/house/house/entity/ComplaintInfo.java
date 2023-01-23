package com.house.house.entity;

public class ComplaintInfo {
    private int cplId;
    private Complaint complaint;
    private House house;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getCplId() {
        return cplId;
    }

    public void setCplId(int cplId) {
        this.cplId = cplId;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public ComplaintInfo(int cplId, Complaint complaint, House house) {
        this.cplId = cplId;
        this.complaint = complaint;
        this.house = house;
    }

    public ComplaintInfo() {
    }
}
