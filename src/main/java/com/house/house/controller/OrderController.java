package com.house.house.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.house.house.entity.*;
import com.house.house.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class OrderController {

	@Autowired
	private IOrderService sevice;
	
	@RequestMapping("/myorder")
	public String toOrderPage() {
		return "myorder";
	}


	@RequestMapping("/updatepwd")
	public String toUpdatepwdPage() {
		return "updatepwd";
	}

	/**
	 * 获取用户该房子是否预定
	 * @return
	 */
	@GetMapping("/getUserOrder")
	@ResponseBody
	public Order getUserOrder(String hid,HttpServletRequest request){
		Users u =(Users) request.getSession().getAttribute("loginUser");
		System.out.println(Integer.parseInt(hid));
		try {
			Order order = sevice.getUserOrder(u.getuID(),Integer.parseInt(hid));
			if(order!=null){
				return order;
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 预定房子
	 * @return
	 */
	@RequestMapping("/addOrder")
	@ResponseBody
	public String addOrder(String id,HttpServletRequest request) {
		Users u = (Users) request.getSession().getAttribute("loginUser");
		try {
			Order order = new Order();
			order.sethID(Integer.parseInt(id));
			order.setOrderUser(u.getuNickName());
			order.setuID(u.getuID());

			int n = sevice.addOrder(order);
			if(n>0) {
				return "OK";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "FAIL";
	}

	/**
	 * 预定信息查看
	 * @param page
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping("/myOrderInfo")
	@ResponseBody
	public UserOrderData findAllOrder(int page, int limit, HttpServletRequest request){
		Page p = new Page();
		p.setPage((page - 1) * limit);
		p.setLimit(limit);
		Users u = (Users) request.getSession().getAttribute("loginUser");
		p.setuID(u.getuID());
		UserOrderData uod = new UserOrderData();
		List<UserOrder> order = sevice.findAllOrder(p);
		uod.setCode(0);
		uod.setCount(sevice.getOrderCount(u.getuID()));
		uod.setData(order);
		uod.setMsg("200");
		return  uod;
	}

	/**
	 * 删除预定房源
	 * @param oID
	 * @return
	 */
	@RequestMapping("/deleteOrder")
	@ResponseBody
	public String deleteOrder(String oID) {
		int n = sevice.deleteOrder(Integer.parseInt(oID));
		if(n>0)
			return "OK";
		return "FAIL";
	}
}
