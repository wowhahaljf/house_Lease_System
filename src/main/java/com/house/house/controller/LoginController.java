package com.house.house.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.house.house.entity.Msg;
import com.house.house.entity.Users;
import com.house.house.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class LoginController {
	private HttpSession session;
	@Autowired
	private IUserService mapper;

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param req
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String toCustomerPage(String username,String password,HttpServletRequest req,HttpSession session) {
		Users user = new Users();
		user.setuName(username);
		user.setuPassword(password);
		Users loginUser = mapper.login(user);
		if(loginUser!=null) {
			req.getSession().setAttribute("loginUser", loginUser);
			session.setAttribute("loginUser", loginUser);

			return "OK";
		}
		return "FAIL";
	}

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param req
	 * @return
	 */
	@RequestMapping("/login1")
	@ResponseBody
	public Msg toCustomerPage1(String username, String password, HttpServletRequest req) {
		Users user1 = new Users();
		String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
		user1.setuName(username);
		user1.setuPassword(pwd);
		Users loginUser = mapper.login(user1);

		if(loginUser!=null) {
			String name = loginUser.getuName();
			req.getSession().setAttribute("loginUser",loginUser);
			req.getSession().setAttribute("loginUserName",name);
			return new Msg(200,loginUser,name+"登录成功");
		}
		return new Msg(400,null,"登录失败");
	}
	
	@RequestMapping("/signout")
	public String signout(HttpSession session) {
		session.invalidate();
		return "redirect:toIndexPage";
	}

	/**
	 * 用户退出
	 * @param session
	 * @return
	 */
	@RequestMapping("/signOut")
	@ResponseBody
	public String signOut(HttpSession session) {
		session.invalidate();
		return "OK";
	}

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public String regist(@RequestBody Users user) {
		// 将其传输过来的密码进行md5加密
		String password = user.getuPassword();
		String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
		user.setuPassword(pwd);
		int regist;

			regist = mapper.regist(user);
			if(regist>0) {
				return "OK";
			}

		return "FAIL";
	}
	
}
