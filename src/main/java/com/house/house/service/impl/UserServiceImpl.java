package com.house.house.service.impl;

import com.house.house.dao.AdminMapper;
import com.house.house.dao.HouseMapper;
import com.house.house.dao.UserMapper;
import com.house.house.entity.*;
import com.house.house.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private HouseMapper houseMapper;


	
	@Override
	public Users login(Users user) {
		return mapper.login(user);
	}

	@Override
	public int regist(Users user) {
		return mapper.regist(user);
	}

	@Override
	public int updateUserPwd(Users users) {
		return mapper.updateUserPwd(users);
	}

	@Override
	public Users checkOldPwd(Users users) {
		return mapper.checkOldPwd(users);
	}

	@Override
	public Users checkUName(String uName) {
		return mapper.checkUName(uName);
	}

	@Override
	public int updateInfo(Users users) {
		return mapper.updateInfo(users);
	}

	@Override
	public int complainSubmit(Complaint complaint) {
		return mapper.complainSubmit(complaint);
	}

	@Override
	public Msg getComplaint(int uid) {
		List<Complaint> list = mapper.getComplaint(uid);
		List<ComplaintInfo> data =new ArrayList<>();
		if(list!=null){
			for (Complaint complaint :
					list) {
				if(complaint.getAid()!=null){
					ComplaintInfo info = new ComplaintInfo
							(complaint.getCplId(),
									complaint,
									houseMapper.findHouseDetailsById(complaint.getHid()));
					data.add(info);
				}else{
					complaint.setAid(null);
					ComplaintInfo info = new ComplaintInfo
							(complaint.getCplId(),
									complaint,
									houseMapper.findHouseDetailsById(complaint.getHid()));
					data.add(info);
				}
			}
			return new Msg(200,data,"获取成功");
		}else{
			return new Msg(400,null,"获取失败");
		}
	}

	@Override
	public Msg withdrawComplaint(int cplId) {
		int res = mapper.withdrawComplaint(cplId);
		if(res>0){
			return new Msg(200,res,"撤回成功");
		}
		return new Msg(400,res,"撤回失败");
	}
}
