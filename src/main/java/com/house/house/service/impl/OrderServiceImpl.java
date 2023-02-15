package com.house.house.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.house.house.dao.HouseMapper;
import com.house.house.dao.OrderMapper;
import com.house.house.dao.UserMapper;
import com.house.house.entity.*;
import com.house.house.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderMapper mapper;
	@Autowired
	private UserMapper userMapper;

	
	@Override
	public int addOrder(Order order) {
		return mapper.addOrder(order);
	}

	@Override
	public List<UserOrder> findAllOrder(Page page) {
		return mapper.findAllOrder(page);
	}

	@Override
	public int getOrderCount(int uID) {
		return mapper.getOrderCount(uID);
	}

	@Override
	public Msg deleteOrder(int oID) {
		int res = mapper.deleteOrder(oID);
		if (res>0){
			return new Msg(200,res,"删除订单成功");
		}
		return new Msg(400,res,"删除订单失败");
	}

	@Override
	public Order getUserOrder(int uid, int hid) {
		return mapper.getUserOrder(uid,hid);
	}

	@Override
	public Msg orderLook(int hID) {
		List<Order> list = mapper.getHouseByhID(hID);
		List<UserOrderInfo> infoList=new ArrayList<>();
		for (Order order : list) {
			UserOrderInfo info = new UserOrderInfo();
			Users user = userMapper.getUser(order.getuID());
			info.setOrder(order);
			info.setPhone(user.getuPhoneNumber());
			infoList.add(info);
		}
		if(list!=null){
			return new Msg(200,infoList,"查询成功");
		}else{
			return new Msg(400,null,"查询失败");
		}
	}

	@Override
	public Msg processOrder(int oID) {
		int res = mapper.processOrder(oID);
		if(res>0){
			return new Msg(200,res,"确认订单成功");
		}
		return new Msg(400,res,"确认订单失败");
	}

	@Override
	public Msg orderLookByUser(int oID) {
		Order order = mapper.getOrderByOID(oID);
		if(order!=null){
			return new Msg(200,order,"订单查询成功");
		}else{
			return new Msg(400,null,"订单查询失败");
		}
	}
}
