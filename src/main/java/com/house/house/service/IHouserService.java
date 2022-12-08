package com.house.house.service;

import com.house.house.entity.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface IHouserService {
	/**
	 * 首页信息展示
	 * @return
	 */
	public List<House> findHomeInfo();

	/**
	 * 审核展示
	 * @return
	 */
	public List<House> findHomeInfoAudit();


	/**
	 * 通过id查询房屋详情
	 * @param id
	 * @return
	 */
	public House findHouseDetailsById(int id);
	/**
	 * 添加房源信息
	 * @param house
	 * @return
	 */
	public int addNewHouse(House house);

	public List<House> findHouseByUser(Page page);
	/**
	 *  删除用户发布的房源信息
	 * @param hID
	 * @return
	 */
	public int deleteUserHouse(int hID);

	/**
	 * 更新房源信息
	 * @param house
	 * @return
	 */
	public int updateHouse(House house);

	/**
	 * 更新房源信息(用户)
	 * @param house
	 * @return
	 */
	public int updateUserHouse(House house);


	/**
	 * 条件查询
	 * @param keywords
	 * @return
	 */
	public List<House> findHouseByLike(String keywords);
	/**
	 * 降序查询
	 * @return
	 */
	public List<House> findHouseOrderByDesc();
	/**
	 * 升序序查询
	 * @return
	 */
	public List<House> findHouseOrderByAsc();

	/**
	 * cache
	 * @param id
	 * @return
	 */
	House getById(Integer id);

	/**
	 * 查询总数
	 * @param p
	 * @return
	 */
    int countUserHouse(Page p);

	/**
	 * 发布评论
	 * @param comment
	 * @return
	 */
	public int submitUserComment(Comment comment);

	/**
	 * 用户回复
	 * @param reply
	 * @return
	 */
	public int submitOthersComment(Reply reply);


	/**
	 *
	 * @param hId
	 * @return
	 */
	public List<Comment> getCommentList(int hId);

	/**
	 * 获取子级评论
	 * @param cId
	 * @return
	 */
	public List<Reply>getCommentSubList(int cId);

	/**
	 * 增加父级评价数量
	 * @param rId
	 * @return
	 */
	public int addFatherCommentCount(int rId);

	/**
	 * 增加父根级评价数量
	 * @param cId
	 * @return
	 */
	public int addRootCommentCount(int cId);
	/**
	 * 增加父根级点赞数量
	 * @param cId
	 * @return
	 */
	public int addRootLike(CommentLike commentLike);
	/**
	 * 增加父根级点赞数量
	 * @param cId
	 * @return
	 */
	public CommentLike addRootLikeUser(CommentLike commentLike);
	/**
	 * 减少父根级点赞数量
	 * @param commentLike
	 * @return
	 */
	public int cancelRootLike(CommentLike commentLike);

	/**
	 * 更新点赞信息
	 * @param commentLike
	 * @return
	 */
	public int cancelRootLikeUser(CommentLike commentLike);

	/**
	 *
	 * 获取点赞信息
	 * @param commentLike
	 * @return
	 */
	public CommentLike getCommentLikeInfo(CommentLike commentLike);
	/**
	 * 取消之后，再重新点赞
	 * @param commentLike
	 * @return
	 */
	public int afterCancelAddLikeComment(CommentLike commentLike);

	/**
	 * 获取回复点赞数据
	 * @param rId
	 * @param uId
	 * @param rlId
	 * @return
	 */
	 public ReplyLike getReplyLikeInfo(int rId,int uId,int rlId);

	/**
	 * 回复点赞第一次
	 * @param replyLike
	 * @return
	 */
	public int addReplyLikeUser(ReplyLike replyLike);
	/**
	 * 回复点赞第一次，修改reply表点赞数
	 * @param replyLike
	 * @return
	 */
	public ReplyLike addReplyLike(ReplyLike replyLike);

	/**
	 * 取消回复点赞
	 * @param uId
	 * @param rId
	 * @return
	 */
	public int cancelReplyLikeUser(int uId,int rId);
	/**
	 * 取消回复点赞，在reply中修改点赞数
	 * @param rId
	 * @return
	 */
	public int cancelReplyLike(int rId);
	/**
	 * 取消之后，再点赞
	 * @param
	 * @return
	 */
	public int afterCancelAddReplyLike(ReplyLike replyLike);

	/**
	 * 搜索
	 * @param input
	 */
	public List<House> searchHouse(String input);


	/**
	 * 用户给房子点赞
	 * @param uid
	 * @param hid
	 * @return
	 */
	public int houseLike(int uid,int hid);

	/**
	 * 查询用户点赞情况
	 * @param uid
	 * @param hid
	 * @return
	 */
	public Msg getLike(int uid ,int hid);

	/**
	 * 修改点赞
	 * @param id
	 * @return
	 */
	int updateHouseLike(int id);

	/**
	 * 查询用户收藏情况
	 * @param uid
	 * @param hid
	 * @return
	 */
	Msg getCollection(int uid, int hid);

	/**
	 * 修改收藏
	 * @param id
	 * @return
	 */
	int updateHouseCol(int id);

	/**
	 * 收藏房屋
	 * @param getuID
	 * @param hid
	 * @return
	 */
	int insertCol(int getuID, int hid);

	/**
	 * 获取用户收藏列表
	 * @param uid
	 * @return
	 */
	Msg getUserCol(int uid);

	/**
	 * 用户删除收藏
	 * @param uid
	 * @return
	 */
	Msg deleteCol(int uid,int colId);
}
