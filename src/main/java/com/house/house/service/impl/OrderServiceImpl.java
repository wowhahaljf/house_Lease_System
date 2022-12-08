package com.house.house.service.impl;

import java.util.List;

import com.house.house.dao.OrderMapper;
import com.house.house.entity.Order;
import com.house.house.entity.Page;
import com.house.house.entity.UserOrder;
import com.house.house.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderMapper mapper;
	
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
	public int deleteOrder(int oID) {
		return mapper.deleteOrder(oID);
	}

	@Override
	public Order getUserOrder(int uid, int hid) {
		return mapper.getUserOrder(uid,hid);
	}

}
