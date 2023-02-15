package com.house.house.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.house.house.entity.Msg;
import com.house.house.entity.Users;
import com.house.house.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.house.house.utils.ImageValidate.createImage;


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
	/**
	 * @param @return 参数说明
	 * @return BaseRestResult 返回类型
	 * @Description: 生成滑块拼图验证码
	 */
	@RequestMapping(value = "/getImageVerifyCode.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Msg getImageVerifyCode(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		//读取本地路径下的图片,随机选一条
		File file = new File(this.getClass().getResource("../../../../static/img/Validate/").getPath());
		File[] files = file.listFiles();
		int n = new Random().nextInt(files.length);
		File imageUrl = files[n];

		createImage(imageUrl, resultMap);

		//读取网络图片
		//ImageUtil.createImage("http://hbimg.b0.upaiyun.com/7986d66f29bfeb6015aaaec33d33fcd1d875ca16316f-2bMHNG_fw658",resultMap);
		HttpSession session = request.getSession();
		session.setAttribute("xWidth", resultMap.get("xWidth"));
		System.out.println(resultMap.get("xWidth"));
		resultMap.remove("xWidth");
		resultMap.put("errcode", 0);
		resultMap.put("errmsg", "success");
		return new Msg(200,resultMap,"生成滑块拼图验证码");
	}


	/**
	 * 校验滑块拼图验证码
	 *
	 * @param moveLength 移动距离
	 * @return BaseRestResult 返回类型
	 * @Description: 生成图形验证码
	 */
	@RequestMapping(value = "/verifyImageCode.do", method = RequestMethod.GET)
	@ResponseBody
	public Msg verifyImageCode(@RequestParam(value = "moveLength") String moveLength, HttpSession session) {
		System.out.println("验证--》"+moveLength);
		Double dMoveLength = Double.valueOf(moveLength);
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Integer xWidth = (Integer) session.getAttribute("xWidth");
			if (xWidth == null) {
				resultMap.put("errcode", 2);
				resultMap.put("errmsg", "验证过期，请重试");
				return new Msg(200,resultMap,"验证滑块");
			}
			if (Math.abs(xWidth - dMoveLength) > 20) {
				resultMap.put("errcode", 1);
				resultMap.put("errmsg", "验证不通过");
				return new Msg(200,resultMap,"验证滑块");
			} else {
				resultMap.put("errcode", 0);
				resultMap.put("errmsg", "验证通过");
				return new Msg(200,resultMap,"验证滑块");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.removeAttribute("xWidth");
		}
		return new Msg(200,resultMap,"验证滑块");
	}
}
