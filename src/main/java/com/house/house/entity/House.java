package com.house.house.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class House {
	private int hID;
	private String houseDesc;
	private String houseModel;
	private String houseArea;
	private String houseFloor;
	private String houseType;
	private int housePrice;
	private String houseAddress;
	private String houseImage;
	private String communityName;
	private String houseLinkMan;
	private String houseOriented;
	private String houseDetailsImg;
	private String publisher;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date publishTime;
	private String contract;
	private String fileName;
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public int gethID() {
		return hID;
	}
	public void sethID(int hID) {
		this.hID = hID;
	}
	public String getHouseDesc() {
		return houseDesc;
	}
	public void setHouseDesc(String houseDesc) {
		this.houseDesc = houseDesc;
	}
	public String getHouseModel() {
		return houseModel;
	}
	public void setHouseModel(String houseModel) {
		this.houseModel = houseModel;
	}
	public String getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}
	public String getHouseFloor() {
		return houseFloor;
	}
	public void setHouseFloor(String houseFloor) {
		this.houseFloor = houseFloor;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public int getHousePrice() {
		return housePrice;
	}
	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public String getHouseImage() {
		return houseImage;
	}
	public void setHouseImage(String houseImage) {
		this.houseImage = houseImage;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getHouseLinkMan() {
		return houseLinkMan;
	}
	public void setHouseLinkMan(String houseLinkMan) {
		this.houseLinkMan = houseLinkMan;
	}
	public String getHouseOriented() {
		return houseOriented;
	}
	public void setHouseOriented(String houseOriented) {
		this.houseOriented = houseOriented;
	}
	public String getHouseDetailsImg() {
		return houseDetailsImg;
	}
	public void setHouseDetailsImg(String houseDetailsImg) {
		this.houseDetailsImg = houseDetailsImg;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public House(String houseDesc, String houseModel, String houseArea, String houseFloor, String houseType,
			int housePrice, String houseAddress, String houseImage, String communityName, String houseLinkMan,
			String houseOriented, String houseDetailsImg, String publisher, Date publishTime) {
		super();
		this.houseDesc = houseDesc;
		this.houseModel = houseModel;
		this.houseArea = houseArea;
		this.houseFloor = houseFloor;
		this.houseType = houseType;
		this.housePrice = housePrice;
		this.houseAddress = houseAddress;
		this.houseImage = houseImage;
		this.communityName = communityName;
		this.houseLinkMan = houseLinkMan;
		this.houseOriented = houseOriented;
		this.houseDetailsImg = houseDetailsImg;
		this.publisher = publisher;
		this.publishTime = publishTime;
	}
	public House(int hID, String houseDesc, String houseModel, String houseArea, String houseFloor, String houseType,
			int housePrice, String houseAddress, String houseImage, String communityName, String houseLinkMan,
			String houseOriented, String houseDetailsImg, String publisher, Date publishTime) {
		super();
		this.hID = hID;
		this.houseDesc = houseDesc;
		this.houseModel = houseModel;
		this.houseArea = houseArea;
		this.houseFloor = houseFloor;
		this.houseType = houseType;
		this.housePrice = housePrice;
		this.houseAddress = houseAddress;
		this.houseImage = houseImage;
		this.communityName = communityName;
		this.houseLinkMan = houseLinkMan;
		this.houseOriented = houseOriented;
		this.houseDetailsImg = houseDetailsImg;
		this.publisher = publisher;
		this.publishTime = publishTime;
	}

	@Override
	public String toString() {
		return "House{" +
				"hID=" + hID +
				", houseDesc='" + houseDesc + '\'' +
				", houseModel='" + houseModel + '\'' +
				", houseArea='" + houseArea + '\'' +
				", houseFloor='" + houseFloor + '\'' +
				", houseType='" + houseType + '\'' +
				", housePrice=" + housePrice +
				", houseAddress='" + houseAddress + '\'' +
				", houseImage='" + houseImage + '\'' +
				", communityName='" + communityName + '\'' +
				", houseLinkMan='" + houseLinkMan + '\'' +
				", houseOriented='" + houseOriented + '\'' +
				", houseDetailsImg='" + houseDetailsImg + '\'' +
				", publisher='" + publisher + '\'' +
				", publishTime=" + publishTime +
				'}';
	}

	public House() {
		super();
	}
}
