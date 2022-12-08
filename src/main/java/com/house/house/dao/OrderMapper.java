package com.house.house.dao;

import java.util.List;


import com.house.house.entity.Order;
import com.house.house.entity.Page;
import com.house.house.entity.UserOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
	/**
	 *   添加房屋订单
	 * @param order
	 * @return
	 */
	 public int addOrder(Order order);
	 /**
	  * 查询所有订单信息
	  * @return
	  */
	 public List<UserOrder> findAllOrder(Page page);
	 /**
	  * 查询所有订单数
	  * @return
	  */
	 public int getOrderCount(int uID);
	 /**
	  * 删除用户订单
	  * @param oID
	  * @return
	  */
	 public int deleteOrder(int oID);

	 /**
	 * 获取用户房子预定情况
	 * @param hid
	 * @return
			 */
	public Order getUserOrder (int uid,int hid);
}
