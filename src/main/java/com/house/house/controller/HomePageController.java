package com.house.house.controller;



import javax.servlet.http.HttpServletRequest;

import com.house.house.entity.House;
import com.house.house.entity.Msg;
import com.house.house.entity.Users;
import com.house.house.service.IHouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomePageController {
	
	@Autowired
	private IHouserService service;

	
	@RequestMapping("/toIndexPage")
	public String toIndexPage(HttpServletRequest request) {
		List<House> findHomeInfo = service.findHomeInfo();
		request.getSession().setAttribute("House", findHomeInfo);
		return "index";
	}
	
	@RequestMapping("/findHouseByLike")
	public String findHouseByLike(HttpServletRequest request,String keywords) {
		List<House> findHomeInfo = service.findHouseByLike(keywords);
		request.getSession().removeAttribute("House");
		request.getSession().setAttribute("House", findHomeInfo);
		return "index";
	}
	
	@RequestMapping("/findHousrOrderByAsc")
	public String findHousrOrderByAsc(HttpServletRequest request) {
		List<House> findHomeInfo = service.findHouseOrderByAsc();
		request.getSession().removeAttribute("House");
		request.getSession().setAttribute("House", findHomeInfo);
		return "index";
	}
	
	@RequestMapping("/findHousrOrderByDesc")
	public String findHousrOrderByDesc(HttpServletRequest request) {
		List<House> findHomeInfo = service.findHouseOrderByDesc();
		request.getSession().removeAttribute("House");
		request.getSession().setAttribute("House", findHomeInfo);
		return "index";
	}

	/**
	 * 房源按照价格降序排序
	 * @return
	 */
	@GetMapping("/findHousePriceOrderByDesc")
	@ResponseBody
	public List<House> findHousePriceOrderByDesc(){
		List<House> list=service.findHouseOrderByDesc();
		System.out.println(list.toString());
		if(list!=null){
			return list;
		}
		return null;
	}

	/**
	 * 房源按价格升序显示
	 * @return
	 */
	@GetMapping("/findHousePriceOrderByAsc")
	@ResponseBody
	public List<House> findHousePriceOrderByAsc(){
		List<House> list=service.findHouseOrderByAsc();
		if(list!=null){
			return list;
		}
		return null;
	}

	/**
	 * 获取排行榜的信息
	 * @return
	 */
	@GetMapping("/getRankInfo")
	@ResponseBody
	public Msg getRankInfo(){
		return service.getRankInfo();
	}
}
