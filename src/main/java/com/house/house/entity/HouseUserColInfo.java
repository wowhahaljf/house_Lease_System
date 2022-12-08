package com.house.house.entity;

public class HouseUserColInfo {
    private int colId;
    private House house;

    public HouseUserColInfo() {
    }

    public HouseUserColInfo(int colId, House house) {
        this.colId = colId;
        this.house = house;
    }

    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
