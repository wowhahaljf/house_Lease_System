package com.house.house.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserOrder {
	private int oID;
	private int hID;
	private Date orderTime;
	private String orderUser;
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
	private String contract;
	private String publisher;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date end;
	private int money;
	private int status;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public int getoID() {
		return oID;
	}

	public void setoID(int oID) {
		this.oID = oID;
	}

	public int gethID() {
		return hID;
	}

	public void sethID(int hID) {
		this.hID = hID;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
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

	public UserOrder(int hID, Date orderTime, String orderUser, String houseDesc, String houseModel, String houseArea,
			String houseFloor, String houseType, int housePrice, String houseAddress, String houseImage,
			String communityName, String houseLinkMan, String houseOriented) {
		super();
		this.hID = hID;
		this.orderTime = orderTime;
		this.orderUser = orderUser;
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
	}

	public UserOrder(int oID, int hID, Date orderTime, String orderUser, String houseDesc, String houseModel,
			String houseArea, String houseFloor, String houseType, int housePrice, String houseAddress,
			String houseImage, String communityName, String houseLinkMan, String houseOriented) {
		super();
		this.oID = oID;
		this.hID = hID;
		this.orderTime = orderTime;
		this.orderUser = orderUser;
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
	}

	public UserOrder() {
		super();
	}

}
