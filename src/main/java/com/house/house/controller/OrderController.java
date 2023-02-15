package com.house.house.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.house.house.entity.*;
import com.house.house.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Msg addOrder(@RequestBody Order data, HttpServletRequest request) {
		Users u = (Users) request.getSession().getAttribute("loginUser");
		try {
			Order order = new Order();
			order.sethID(data.gethID());
			order.setOrderUser(u.getuName());
			order.setuID(u.getuID());
			order.setStart(data.getStart());
			order.setEnd(data.getEnd());
			order.setMoney(data.getMoney());
			int n = sevice.addOrder(order);
			if(n>0) {
				return new Msg(200,order,"预定订单提交");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return new Msg(400,null,"预定失败");
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
	public Msg deleteOrder(String oID) {
		return sevice.deleteOrder(Integer.parseInt(oID));
	}

	/**
	 * 用户查看订单（发布人）
	 * @param hID
	 * @return
	 */
	@RequestMapping("/orderLook")
	@ResponseBody
	public Msg orderLook(int hID){
		return sevice.orderLook(hID);
	}

	/**
	 * 用户查询单条订单
	 * @param oID
	 * @return
	 */
	@RequestMapping("/orderLookByUser")
	@ResponseBody
	public Msg orderLookByUser(int oID){
		return sevice.orderLookByUser(oID);
	}

	/**
	 * 确认订单
	 * @param oID
	 * @return
	 */
	@RequestMapping("/processOrder")
	@ResponseBody
	public Msg processOrder(String oID){
		return sevice.processOrder(Integer.parseInt(oID));
	}


}
