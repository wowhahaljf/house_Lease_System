package com.house.house.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.house.house.dao.HouseMapper;
import com.house.house.entity.*;
import com.house.house.service.IHouserService;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HouserServiceImpl implements IHouserService {

	@Autowired
	private HouseMapper dao;

	//提供缓存
	private HashMap<Integer,House>cache=new HashMap<Integer,House>();

	@Override
	public House getById(Integer id) {
		//提供缓存，如果当前缓存中没有要查询的数据，则进行查询，并放入进缓存中，否则直接从缓存中获取数据返回
	House house =cache.get(id);
	if(house==null){
		House queryHouse=dao.findHouseDetailsById(id);
		cache.put(id,queryHouse);
		return queryHouse;
	}
		return house;
	}

	@Override
	public int countUserHouse(Page p) {
		return dao.countUserHouse(p);
	}



	@Override
	public List<House> findHomeInfo() {
		return dao.findHomeInfo();
	}

	@Override
	public List<House> findHomeInfoAudit() {
		return dao.findHomeInfoAudit();
	}

	@Override
	public House findHouseDetailsById(int id) {
		return dao.findHouseDetailsById(id);
	}

	@Override
	public int addNewHouse(House house) {
		return dao.addNewHouse(house);
	}

	@Override
	public List<House> findHouseByUser(Page page) {
		return dao.findHouseByUser(page);
	}
	
	@Override
	public int deleteUserHouse(int hID) {
		return dao.deleteUserHouse(hID);
	}
	@Override
	public int updateHouse(House house) {
		return dao.updateHouse(house);
	}

	@Override
	public int updateUserHouse(House house) {
		return dao.updateUserHouse(house);
	}

	@Override
	public List<House> findHouseByLike(String keywords) {
		return dao.findHouseByLike(keywords);
	}
	@Override
	public List<House> findHouseOrderByAsc() {
		return dao.findHouseOrderByAsc();
	}
	@Override
	public List<House> findHouseOrderByDesc() {
		return dao.findHouseOrderByDesc();
	}
	@Override
	public int submitUserComment(Comment comment) {
		return dao.submitUserComment(comment);
	}

	@Override
	public int submitOthersComment(Reply reply) {
		return dao.submitOthersComment(reply);
	}

	@Override
	public List<Comment> getCommentList(int hId) {
		return dao.getCommentList(hId);
	}

	@Override
	public List<Reply> getCommentSubList(int cId) {
		return dao.getCommentSubList(cId);
	}

	@Override
	public int addFatherCommentCount(int rId) {
		return dao.addFatherCommentCount(rId);
	}

	@Override
	public int addRootCommentCount(int cId) {
		return dao.addRootCommentCount(cId);
	}

	@Override
	public int addRootLike(CommentLike commentLike) {
		return dao.addRootLike(commentLike);
	}

	@Override
	public CommentLike addRootLikeUser(CommentLike commentLike) {
		 dao.addRootLikeUser(commentLike);
		 return dao.getClId(commentLike);
	}

	@Override
	public int cancelRootLike(CommentLike commentLike) {
		return dao.cancelRootLike(commentLike);
	}

	@Override
	public int cancelRootLikeUser(CommentLike commentLike) {
		return dao.cancelRootLikeUser(commentLike);
	}

	@Override
	public CommentLike getCommentLikeInfo(CommentLike commentLike) {
		return dao.getLikeStatus(commentLike);
	}

	@Override
	public int afterCancelAddLikeComment(CommentLike commentLike) {
		return dao.afterCancelAddLikeComment(commentLike);
	}

	@Override
	public ReplyLike getReplyLikeInfo(int rId, int uId, int rlId) {
		return dao.getReplyLikeInfo(rId,uId,rlId);
	}

	@Override
	public int addReplyLikeUser(ReplyLike replyLike) {
		return dao.addReplyLikeUser(replyLike);
	}

	@Override
	public ReplyLike addReplyLike(ReplyLike replyLike) {
		int n=dao.addReplyLike(replyLike);
		if(n>0){
			int rId=replyLike.getrId();
			int uId=replyLike.getuId();
			int rlId=replyLike.getRlId();
			ReplyLike res=dao.getReplyLikeInfo(rId,uId,rlId);
			if(res!=null){
				return res;
			}
		}
		return null;
	}

	@Override
	public int cancelReplyLikeUser(int uId, int rId) {
		return dao.cancelReplyLikeUser(uId,rId);
	}

	@Override
	public int cancelReplyLike(int rId) {
		return dao.cancelReplyLike(rId);
	}

	@Override
	public int afterCancelAddReplyLike(ReplyLike replyLike) {
		int n= dao.afterCancelAddReplyLike(replyLike.getRlId());
		if(n>0){
			int m=dao.addReplyLike(replyLike);
			if(m>0){
				return n+m;
			}
		}
		return 0;
	}

	@Override
	public List<House> searchHouse(String input) {
		return dao.search(input);

	}

	@Override
	public int houseLike(int uid, int hid) {
		return dao.houseLike(uid,hid);
	}

	@Override
	public Msg getLike(int uid, int hid) {
		try {
			userHouseLike houseLike = dao.getHouseLike(uid, hid);
			if (houseLike!=null){
				return new Msg(200,houseLike,"查询到了");

			}else{
				return new Msg(200,null,"无点赞记录");
			}
		}catch (Exception e){
			return new Msg(400,null,"出错了");
		}

	}

	@Override
	public int updateHouseLike(int id) {
		return dao.updateHouseLike(id);
	}

	@Override
	public Msg getCollection(int uid, int hid) {
		try {
			UserHouseCollection info = dao.getCollectionInfo(uid, hid);
			if(info!=null){
				return new Msg(200,info,"查阅到了");
			}else{
				return new Msg(200,null,"无收藏记录");
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("出错了");
		}
		return new Msg(404,null,"未知错误");
	}

	@Override
	public int updateHouseCol(int id) {
		return dao.updateCol(id);
	}

	@Override
	public int insertCol(int uid, int hid) {
		return dao.insertCol(uid,hid);
	}

	@Override
	public Msg getUserCol(int uid) {
		List<UserHouseCollection> list = dao.getUserCollection(uid);
		List<HouseUserColInfo> data=new ArrayList<>();
		if(list!=null){
			for (UserHouseCollection c :
					list) {
				HouseUserColInfo houseUserColInfo = new HouseUserColInfo(c.getuColId(), dao.findHouseDetailsById(c.getHid()));
				data.add(houseUserColInfo);//添加收藏的房屋详细信息
			}
			return new Msg(200,data,"获取成功");
		}else{
			return new Msg(404,data,"获取失败");
		}
	}

	@Override
	public Msg deleteCol(int uid,int colId) {
		int res = dao.deleteCol(colId);
		if(res>0){
			return  new Msg(200,res,"删除成功");
		}
		return new Msg(404,res,"删除失败");
	}
}
