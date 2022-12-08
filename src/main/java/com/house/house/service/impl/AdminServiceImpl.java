package com.house.house.service.impl;



import com.house.house.dao.AdminMapper;
import com.house.house.entity.Admin;
import com.house.house.entity.House;
import com.house.house.entity.Page;
import com.house.house.entity.Users;
import com.house.house.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminMapper service;


	@Override
	public Admin adminAccess(Admin admin) {
		return service.adminAccess(admin);
	}

	@Override
	public List<Users> findAllUser() {
		return service.findAllUser();
	}

	@Override
	public List<House> findAllHouse(Page page) {
		return service.findAllHouse(page);
	}

	@Override
	public int deleteHouse(int hID) {
		return service.deleteHouse(hID);
	}

	@Override
	public int deleteHouseBatch(String[] ids) {
		return service.deleteHouseBatch(ids);
	}

	@Override
	public Users findUserById(int uID) {
		return service.findUserById(uID);
	}

	@Override
	public int updateUser(Users users) {
		return service.updateUser(users);
	}
	@Override
	public int deleteUser(int uID) {
		return service.deleteUser(uID);
	}

	@Override
	public int deleteByIdIs(String []ids) {
		return service.deleteByIdIs(ids);

	}

	@Override
	public List<Users> selectPage(Integer pageNum, Integer pageSize) {
		return service.selectPage(pageNum,pageSize);
	}

	@Override
	public Integer selectTotal() {
		return service.selectTotal();
	}

	@Override
	public Admin checkAdminPwd(Admin admin) {
		return service.checkAdminPwd(admin);
	}
	@Override
	public int updateAdminPwd(Admin admin) {
		return service.updateAdminPwd(admin);
	}

	@Override
	public int auditHouse(int hid) {
		return service.auditHouse(hid);
	}

	@Override
	public int auditHouseNoPass(int hid) {
		return service.auditHouseNoPass(hid);
	}
}
