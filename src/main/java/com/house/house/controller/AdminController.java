package com.house.house.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.house.house.entity.Admin;
import com.house.house.entity.House;
import com.house.house.entity.Page;
import com.house.house.entity.UserData;
import com.house.house.entity.UserHouseData;
import com.house.house.entity.Users;
import com.house.house.service.IAdminService;
import com.house.house.service.IHouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

	@Autowired
	private IAdminService service;

	@Autowired
	private IHouserService dao;

	/**
	 * 跳转到登录页
	 * 
	 * @return
	 */
	@RequestMapping("/toAdminLogin")
	public String toAdminLogin() {
		return "admin";
	}

	/**
	 * 管理员登录
	 * 
	 * @param username
	 * @param userpwd
	 * @param req
	 * @return
	 */
	@RequestMapping("/adminAccess")
	@ResponseBody
	public String adminAccess(String username, String userpwd, HttpServletRequest req) {
		Admin admin = new Admin(username, userpwd);
		Admin adminAccess = service.adminAccess(admin);//[从数据库中查询用户名和密码一致的用户]
		req.getSession().setAttribute("Admin", adminAccess);
		if (adminAccess != null)
			return "OK";//[如果返回是OK，则会回调，重新跳转到toAdminHomePage]
		return "FAIL";
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminSingnout")
	public String signout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "admin";
	}

	/**
	 *退出登录（管理员）
	 * @param request
	 * @return
	 */
	@PostMapping ("/adminSignOut")
	@ResponseBody
	public String signOut1(HttpServletRequest request) {
		request.getSession().invalidate();
		Object name =request.getSession().getAttribute("Admin");
		System.out.println(name);
		if(name==null){
			return "OK";
		}
		return "FAIL";
	}

	@RequestMapping("/toAdminHomePage")
	public String toAdminHomePage() {
		return "adminhome";
	}

	@RequestMapping("/toAllUserPage")
	public String toAllUserPage() {
		return "alluser";
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@RequestMapping("/AllUsers")
	@ResponseBody
	public UserData findAllUser() {
		List<Users> findAllUser = service.findAllUser();
		UserData u = new UserData();
		u.setCode(0);
		u.setCount(findAllUser.size());
		u.setData(findAllUser);
		u.setMsg("OK");
		return u;
	}

	@RequestMapping("/toAllHousePage")
	public String toAllHousePage() {
		return "allhouse";
	}

	/**
	 * 查询所有房源
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/findAllHouse")
	@ResponseBody
	public UserHouseData findAllHouse(int page, int limit) {
		Page p = new Page();
		p.setLimit(limit);
		p.setPage((page - 1) * limit);
		List<House> findAllHouse = service.findAllHouse(p);
		UserHouseData data = new UserHouseData();
		List<House> houseList=dao.findHomeInfoAudit();
		data.setCode(0);
		data.setCount(houseList.size());
		data.setData(findAllHouse);
		data.setMsg("OK");
		return data;
	}

	/**
	 * 删除房源
	 * 
	 * @param hID
	 * @return
	 */
	@RequestMapping("/deleteHouse")
	@ResponseBody
	public String deleteHouse(int hID) {
		int deleteHouse = service.deleteHouse(hID);
		if (deleteHouse > 0) {
			return "OK";
		}
		return "FAIL";
	}

	/**
	 * 批量删除房源
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/deleteHouseBatch/{ids}")
	@ResponseBody
	public String deleteHouseBatch(@PathVariable("ids") String ids){
		String []spilt =ids.split(",");

		if(service.deleteHouseBatch(spilt)==spilt.length){
			return "ok";
		}
		return "fail";
	}
	/**
	 * 跳转到管理员更新房源界面
	 * 
	 * @param hID
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAdminUpdateHousePage")
	public String toUpdatePage(int hID, HttpServletRequest request) {
		House house = dao.findHouseDetailsById(hID);
		request.getSession().setAttribute("House", house);
		return "updatehouse";
	}

	/**
	 * 传入id,跳转到修改用户界面
	 * 
	 * @return
	 */
	@RequestMapping("/toEditUserPage")
	public String toEditUserPage(int uID, HttpServletRequest req) {
		Users findUserById = service.findUserById(uID);
		req.getSession().setAttribute("User", findUserById);
		return "editUser";
	}

	/**
	 * 更新用户信息
	 * 
	 * @param users
	 * @return
	 */
	@RequestMapping("/editUser")
	@ResponseBody
	public String editUser(Users users) {
		int n = service.updateUser(users);
		if (n > 0)
			return "OK";
		return "FAIL";
	}

	/**
	 * 管理员删除用户
	 * 
	 * @param uID
	 * @return
	 */
	@RequestMapping("/deleteUser/{uID}")
	@ResponseBody
	public String deleteUser(@PathVariable int uID) {
		int n = service.deleteUser(uID);
		if (n > 0) {
			return "OK";
		}
		return "FAIL";
	}

	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser1(int uID) {
		int n = service.deleteUser(uID);
		if (n > 0) {
			return "OK";
		}
		return "FAIL";
	}

	/**
	 * 批量删除
	 *

	 */
	@DeleteMapping("/deleteUsers/{ids}")
	@ResponseBody
	public String DelById(@PathVariable("ids") String ids){
		System.out.println(ids);
		String[] split = ids.split(",");
		if(service.deleteByIdIs(split)==split.length){
			return "ok";
		}
		return "fail";
	}

	/**
	 * 用户信息分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/selectPage")
	@ResponseBody
	public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
		pageNum=(pageNum-1)*pageSize;
		HashMap<String, Object> map = new HashMap<>();
		map.put("total",service.selectTotal());
		map.put("data",service.selectPage(pageNum,pageSize));
		return map;
	}

	@RequestMapping("/toUpdateAdminPwdPage")
	public String toUpdateAdminPwdPage() {
		return "updateAdminPwd";
	}
	@RequestMapping("/welcome01")
	public String toWelcomePage() {
		return "welcome01";
	}

	@RequestMapping("/updateAdminPwd")
	@ResponseBody
	public String updateAdminPwd(HttpServletRequest request,String oldpwd, String newpwd, String newpwdagain) {
		Admin admin = new Admin();
		Admin adminSession = (Admin) request.getSession().getAttribute("Admin");
		admin.setId(adminSession.getId());
		admin.setUserpwd(oldpwd);
		Admin checkAdminPwd = service.checkAdminPwd(admin);
		if (checkAdminPwd == null) {
			return "ERROR";
		}
		if (!newpwd.equals(newpwdagain)) {
			return "FAIL";
		}
		Admin a = new Admin();
		a.setId(adminSession.getId());
		a.setUserpwd(newpwd);
		int n = service.updateAdminPwd(a);
		if (n > 0)
			return "OK";
		return "FAIL";
	}

	/**
	 * 审核房源
	 * @param hid
	 * @return
	 */
	@RequestMapping("/auditHouse")
	@ResponseBody
	public String auditHouse(int hid){
		int res = service.auditHouse(hid);
		if(res>0){
			return "OK";
		}
		return "FAIL";
	}
	/**
	 * 审核房源不通过
	 * @param hid
	 * @return
	 */
	@RequestMapping("/auditHouseNoPass")
	@ResponseBody
	public String auditHouseNoPass(int hid){
		int res = service.auditHouseNoPass(hid);
		if(res>0){
			return "OK";
		}
		return "FAIL";
	}


}
