package com.house.house.service;


import com.house.house.entity.Complaint;
import com.house.house.entity.Msg;
import com.house.house.entity.Users;


public interface IUserService {
	/**
	 *  用户登录
	 * @return
	 */
	public Users login(Users user);
	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public int regist(Users user);
	/**
	 * 修改密码
	 * @return
	 */
	public int updateUserPwd(Users users);
	/**
	 * 检查旧密码
	 * @param users
	 * @return
	 */
	public Users checkOldPwd(Users users);
	/**
	 * 检查用户名是否占用
	 * @param uName
	 * @return
	 */
	public Users checkUName(String uName);
	/**
	 * 修改个人信息
	 * @param users
	 * @return
	 */
	public int updateInfo(Users users);

	/**
	 * 提交投诉表单
	 * @param complaint
	 * @return
	 */
	public int complainSubmit(Complaint complaint);

	/**
	 * 获取投诉
	 * @param uid
	 * @return
	 */
    Msg getComplaint(int uid);

	/***
	 * 撤回投诉
	 * @param cplId
	 * @return
	 */
	Msg withdrawComplaint(int cplId);
}
