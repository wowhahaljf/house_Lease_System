package com.house.house.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class Order {
	private int oID;
	private int hID;
	private int uID;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date orderTime;
	private String orderUser;


	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date start;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date end;

	private int money;
	private int status;//订单状态

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

	public int getuID() {
		return uID;
	}

	public void setuID(int uID) {
		this.uID = uID;
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

	public Order(int hID, int uID, Date orderTime, String orderUser) {
		super();
		this.hID = hID;
		this.uID = uID;
		this.orderTime = orderTime;
		this.orderUser = orderUser;
	}

	public Order(int oID, int hID, int uID, Date orderTime, String orderUser) {
		super();
		this.oID = oID;
		this.hID = hID;
		this.uID = uID;
		this.orderTime = orderTime;
		this.orderUser = orderUser;
	}

	public Order() {
		super();
	}
}
