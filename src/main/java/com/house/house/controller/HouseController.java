package com.house.house.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.house.house.entity.*;
import com.house.house.service.IHouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HouseController {
	
	@Autowired
	private IHouserService service;

	/**
	 * 搜索
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public UserHouseData searchHouse(String input){
		List<House> list = service.searchHouse(input);
		if(list.size()>0){
			return new UserHouseData(1,"搜索成功",list.size(),list);
		}else if(list.size()==0){
			return new UserHouseData(0,"无结果",list.size(),list);
		}
		return new UserHouseData(-1,"错误",list.size(),list);
	}

	/**
	 * 查看用户发布的房源信息
	 * @param request
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/findUserHouse")
	@ResponseBody
	public UserHouseData houseByUser(HttpServletRequest request, int page, int limit) {
		Page p = new Page();
		Users u = (Users) request.getSession().getAttribute("loginUser");
//		Users u1  = (Users) request.getSession().getAttribute("uID");
		String publisher = u.getuName();
		p.setPublisher(publisher);
		p.setLimit(limit);
		p.setPage((page - 1) * limit);//表记录的起始位置=(当前页数-1)*每页大小
		List<House> list = service.findHouseByUser(p);
		int count=service.countUserHouse(p);
		UserHouseData data = new UserHouseData(0, "200",count, list);
		return data;
	}

	/**
	 * 删除
	 * @param hID
	 * @return
	 */
	@RequestMapping("/deleteUserHouse")
	@ResponseBody
	public String deleteUserHouse(String hID) {
		int n = service.deleteUserHouse(Integer.parseInt(hID));
		if(n>0) {
			return "OK";
		}
		return "FAIL";
	}

	/**
	 * 更新发布房源（用户）
	 * @param house
	 * @return
	 */
	@RequestMapping("/updateHouse")
	@ResponseBody
	public String updateHouse(@RequestBody House house) {
		int n = service.updateHouse(house);
		if(n>0)
			return "OK";
		return "FAIL";
	}

	/**
	 * 更新发布房源1（用户）
	 * @param house
	 * @return
	 */
	@RequestMapping("/updateUserHouse")
	@ResponseBody
	public String updateUserHouse(@RequestBody House house) {
		int n = service.updateUserHouse(house);
		if(n>0)
			return "OK";
		return "FAIL";
	}

	/**
	 * 获取用户收藏列表
	 * @param uid
	 * @return
	 */
	@RequestMapping("/getUserCol")
	@ResponseBody
	public Msg getUserCol(int uid){
		Msg msg = service.getUserCol(uid);
		return msg;
	}

	/**
	 * 用户删除收藏
	 * @param uid
	 * @return
	 */
	@RequestMapping("/deleteUserCol")
	@ResponseBody
	public Msg deleteUserCol(int uid,int colId){
		Msg msg =service.deleteCol(uid,colId);
		return msg;
	}


}
