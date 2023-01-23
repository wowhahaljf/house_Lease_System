package com.house.house.service;


import com.house.house.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface IAdminService {
	
	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	public Admin adminAccess(Admin admin);
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<Users> findAllUser();
	
	/**
	 * 查询所有房源信息
	 * @return
	 */
	public List<House> findAllHouse(Page page);
	/**
	 * 管理员删除房源信息
	 * @return
	 */
	public int deleteHouse(int hID);

	/**
	 * 批量删除房源信息
	 * @param ids
	 * @return
	 */
	public int deleteHouseBatch(String []ids);
	/**
	 * 通过id查询用户
	 * @param uID
	 * @return
	 */
	public Users findUserById(int uID);
	/**
	 * 管理员更新普通用户信息
	 * @return
	 */
	public int updateUser(Users users);
	/**
	 * 管理员删除用户
	 * @param uID
	 * @return
	 */
	public int deleteUser(int uID);

	/**
	 * 批量删除用户
	 *
	 * @param ids
	 */
	int deleteByIdIs( String []ids);

	/**
	 * 分页
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Users> selectPage(Integer pageNum,Integer pageSize);

	/**
	 * 用户信息分页总数
	 * @return
	 */
	Integer selectTotal();


	/**
	 *  检查修改密码原密码
	 * @param admin
	 * @return
	 */
	public Admin checkAdminPwd(Admin admin);
	
	/**
	 *  修改管理员密码
	 * @param admin
	 * @return
	 */
	public int updateAdminPwd(Admin admin);

	/**
	 * 审核
	 * @param hid
	 * @return
	 */
	public int auditHouse(int hid);

	/**
	 * 审核不通过
	 * @param hid
	 * @return
	 */
	public int auditHouseNoPass(int hid);

	/**
	 * 分页查询用户投诉
	 * @param page
	 * @param limit
	 * @return
	 */
    Msg getAllComplaint(int page, int limit);

	/**
	 * 更改状态
	 * @param cplId
	 * @param aid
	 * @param status
	 * @return
	 */
    Msg changeStatus(int cplId, int aid, int status);

	/**
	 * 删除投诉
	 * @param cplId
	 * @return
	 */
	Msg deleteComplaint(int cplId);

	/**
	 * 管理查看自己管理的投诉
	 * @param
	 * @return
	 */
	Msg findComplaintByAdmin(int limit,int page,int aid);
}
