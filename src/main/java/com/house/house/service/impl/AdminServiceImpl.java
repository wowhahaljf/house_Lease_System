package com.house.house.service.impl;



import com.house.house.dao.AdminMapper;
import com.house.house.dao.HouseMapper;
import com.house.house.entity.*;
import com.house.house.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminMapper service;
	@Autowired
	private HouseMapper houseMapper;


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

	@Override
	public Msg getAllComplaint(int page, int limit) {
		Page p = new Page();
		p.setLimit(limit);
		p.setPage((page - 1) * limit);//表记录的起始位置=(当前页数-1)*每页大小
		List<Complaint> list = service.getAllComplaint(p);
		List<ComplaintInfo> data = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if (list != null) {
			for (Complaint c :
					list) {
//				if (c.getAid() == null) {
//					c.setAid(null);
//				}
				ComplaintInfo info = new ComplaintInfo(c.getCplId(),
				c, houseMapper.findHouseDetailsById(c.getHid()));
				data.add(info);
			}

			map.put("count", service.getAllComplaintCount());//记录总数
			map.put("list",data);
			return new Msg(200, map, "查询成功");
		}
		return new Msg(400,null,"查询失败");
		}

	@Override
	public Msg changeStatus(int cplId, int aid, int status) {
		int res = service.changeStatus(cplId, aid, status);
		if(res>0){
			return new Msg(200,res,"更改成功");
		}
		return new Msg(400,res,"更改失败");
	}

	@Override
	public Msg deleteComplaint(int cplId) {
		int res = service.deleteComplaint(cplId);
		if(res>0){
			return new Msg(200,res,"删除成功");
		}
		return new Msg(400,res,"删除失败");
	}

	@Override
	public Msg findComplaintByAdmin(int limit,int page,int aid) {
		Page p=new Page();
		p.setPage((page - 1) * limit);
		p.setLimit(limit);
		p.setuID(aid);//暂时代替一下
		List<Complaint> list = service.findComplaintByAdmin(p);
		if(list!=null){
			List<ComplaintInfo> data = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			for (Complaint c :
					list) {
				data.add(new ComplaintInfo(c.getCplId(),
						c,houseMapper.findHouseDetailsById(c.getHid())));
			}
			map.put("list",data);
			map.put("count",service.findComplaintByAdminCount(aid));
			return new Msg(200,map,"查看成功");

		}
		return new Msg(400,null,"查看失败");

	}
}
