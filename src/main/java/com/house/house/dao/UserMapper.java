package com.house.house.dao;


import com.house.house.entity.Complaint;
import com.house.house.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
	/**
	 * 用户登录
	 * 
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

    public int complainSubmit(Complaint complaint);


    List<Complaint> getComplaint(int uid);

	int withdrawComplaint(int cplId);


	Users getUser(int uID);
}
