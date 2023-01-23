package com.house.house.dao;

import com.house.house.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface AdminMapper {
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
	int deleteByIdIs(String []ids);

	/**
	 *
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */

	List<Users> selectPage(Integer pageNum,Integer pageSize);


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
	 * 审核房屋
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
	 * 查询名字
	 * @param aid
	 * @return
	 */
	public String checkAdminName(int aid);

	/**
	 * 获取所有投诉
	 * @param page
	 * @return
	 */
	List<Complaint> getAllComplaint(Page page);

	/**
	 * 获取数量
	 * @return
	 */
	int getAllComplaintCount();
	
    int changeStatus(int cplId, int aid, int status);

	int deleteComplaint(int cplId);

    List<Complaint> findComplaintByAdmin(Page p);

	int findComplaintByAdminCount(int aid);
}
