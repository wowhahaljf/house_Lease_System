package com.house.house.controller;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.house.house.dao.HouseMapper;
import com.house.house.entity.*;
import com.house.house.service.IHouserService;
import com.house.house.service.impl.LikeService;
import com.house.house.utils.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HouseDetailsController {

    @Autowired
    private IHouserService service;
    @Autowired
    private HouseMapper dao;

    @Autowired
    private LikeService likeService;

    /**
     * 获取首页房源信息
     *
     * @return
     */
    @PostMapping("/toIndexPageInfo")
    @ResponseBody
    public List<House> getHouseDetail() {
        List<House> houseList = service.findHomeInfo();
        return houseList;
    }


    /**
     * 审核房屋信息展示
     *
     * @return
     */
    @PostMapping("/toIndexPageInfoAudit")
    @ResponseBody
    public List<House> getHouseDetailAudit() {
        List<House> houseList = service.findHomeInfoAudit();
        return houseList;
    }


    @RequestMapping("/toDetailsPage")
//	@ResponseBody
    public String toDetailsPage(int id, HttpServletRequest request) {
        House HouseDetails = service.findHouseDetailsById(id);
        List<String> list = new ArrayList<String>();
        System.out.println(HouseDetails.toString());
        String[] split = HouseDetails.getHouseDetailsImg().split("~");
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }

        request.getSession().setAttribute("Details", HouseDetails);
        request.getSession().setAttribute("DetailsImg", list);
        return "housedetails";
    }

    /**
     * 通过Id，获取详细房源信息
     * @param id
     * @return
     */
    @RequestMapping("/toDetailsPage01")
    @ResponseBody
    public Msg toDetailsPage01(int id) {
        House HouseDetail = service.findHouseDetailsById(id);
        if (HouseDetail != null) {
            Map<String,Object> LikeNumber = new HashMap<String,Object>();
            List<userHouseLike> list = service.getHouseLikeNum(id);
            System.out.println(list.toString());
            System.out.println(list.size());
            //获取list的长度
            LikeNumber.put("likeNumber", list.size());
            Object obj= ReflectUtil.getObject(HouseDetail,LikeNumber);
            return new Msg(200, obj, "获取成功");
        } else {
            return new Msg(400, null, "失败");
        }
    }

    /**
     * 房屋点赞（缓存）
     *
     * @param hid
     * @param req
     * @return
     */
    @RequestMapping("/likeHouse1")
    @ResponseBody
    public Msg likeHouse1(int hid, HttpServletRequest req) {
        Users user = (Users) req.getSession().getAttribute("loginUser");
        int res = likeService.like(user.getuID(), "houselike", hid);
        if (res == 0) {
            return new Msg(200, res, "取消点赞");
        } else {
            return new Msg(200, res, "点赞");
        }
    }

    /**
     * 房屋点赞
     *
     * @param hid
     * @return
     */
    @RequestMapping("/likeHouse")
    @ResponseBody
    public Msg likeHouse(int hid, HttpServletRequest request) {

        Users user = (Users) request.getSession().getAttribute("loginUser");
        Msg msg = service.getLike(user.getuID(), hid);
        System.out.println(msg.toString());
        //先查询数据库里有无点赞记录
        if (msg.getData() == null) {
            int res = service.houseLike(user.getuID(), hid);
            if (res > 0) {
                return new Msg(200, res, "点赞成功");
            }
            return new Msg(400, res, "点赞失败");
        } else {
            userHouseLike data = (userHouseLike) msg.getData();
            int res = service.updateHouseLike(data.getuLikeId());
            if (data.getIsLike() == 1) {
                if (res > 0) {
                    return new Msg(200, res, "取消点赞成功");
                } else {
                    return new Msg(400, res, "取消点赞失败");
                }
            } else {
                if (res > 0) {
                    return new Msg(200, res, "点赞成功");
                } else {
                    return new Msg(400, res, "点赞失败");
                }
            }

        }

    }

    /**
     * 查询点赞用户情况
     *
     * @param uid
     * @param hid
     * @return
     */
    @RequestMapping("/getLike")
    @ResponseBody
    public Msg getLike(int uid, int hid) {
        System.out.println("---->" + uid);
        Msg msg = service.getLike(uid, hid);
        return msg;
    }

    /**
     * 获取用户收藏情况
     *
     * @param uid
     * @param hid
     * @return
     */
    @RequestMapping("/getCollection")
    @ResponseBody
    public Msg getCollection(int uid, int hid) {
        Msg msg = service.getCollection(uid, hid);
        return msg;
    }

    /**
     * 收藏房屋
     *
     * @param hid
     * @param request
     * @return
     */
    @RequestMapping("/houseCollecion")
    @ResponseBody
    public Msg houseCollection(int hid, HttpServletRequest request) {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        Msg msg = service.getCollection(user.getuID(), hid);
        //查询数据库是否有数据
        if (msg.getData() != null) {
            UserHouseCollection data = (UserHouseCollection) msg.getData();
            service.updateHouseCol(data.getuColId());
            if (data.getIsCol() == 1) {
                return new Msg(200, 1, "取消收藏成功");
            } else {
                return new Msg(200, 1, "收藏成功");
            }
        } else {
            int res = service.insertCol(user.getuID(), hid);
            if (res > 0) {
                return new Msg(200, res, "收藏成功");
            } else {
                return new Msg(404, res, "收藏失败");
            }

        }
    }

    /**
     * 发布评论
     *
     * @param comment
     * @return
     */
    @RequestMapping("/submitComment")
    @ResponseBody
    public String submitComment(@RequestBody Comment comment) {
        int n = service.submitUserComment(comment);
        if (n > 0) {
            return "OK";
        }
        return "FAIL";
    }

    /**
     * 回复评论
     *
     * @param reply
     * @return
     */
    @RequestMapping("/replyComment")
    @ResponseBody
    public String replyComment(@RequestBody Reply reply) {
        int n = service.submitOthersComment(reply);//提交子级评论
//		FromReplyId是0时，就是父根级评论
        if (n > 0) {
            if (reply.getFromReplyId() == 0) {
                service.addRootCommentCount(reply.getcId());
                return "OK";
            } else {
                System.out.println(reply.getFromId());
                service.addFatherCommentCount(reply.getFromReplyId());
                return "OK";
            }
        }
        return "FAIL";
    }

    /**
     * @param uId
     * @param cId
     * @return 是否点赞
     */
    @RequestMapping("/getCommentLikeInfo")
    @ResponseBody
    public int getCommentLikeInfo(int uId, int cId, int clId) {
        CommentLike commentLike = new CommentLike(clId, cId, uId);
        System.out.println("--------------------");
        CommentLike like = service.getCommentLikeInfo(commentLike);
        System.out.println("--------------------");
        if (like != null) {
            return like.getLikeStatus();
        }
        return -1;
    }

    /**
     * 根级评论点赞数增加
     *
     * @return
     */
    @RequestMapping("/addRootLike")
    @ResponseBody
    public CommentLike addRootLike(@RequestBody CommentLike commentLike) {
//		System.out.println(commentLike);
        if (commentLike.getLikeStatus() == 1) {
            CommentLike commentLike1 = service.addRootLikeUser(commentLike);
            int m = service.addRootLike(commentLike);
            System.out.println(m);
            System.out.println(commentLike1);
            if (m > 0 && commentLike1 != null) {
                return commentLike1;
            }
        }
        return null;
    }

    /**
     * 取消点赞
     *
     * @param commentLike
     * @return
     */
    @RequestMapping("/cancelRootLike")
    @ResponseBody
    public String cancelRootLike(@RequestBody CommentLike commentLike) {
        if (commentLike.getLikeStatus() == 1) {
            int n = service.cancelRootLike(commentLike);
            int m = service.cancelRootLikeUser(commentLike);
            if (n + m > 1) {
                return "OK";
            }
        }
        return "FAIL";
    }

    /**
     * 取消之后，又点赞父级评论
     *
     * @param commentLike
     * @return
     */
    @RequestMapping("/afterCancelAddRootLike")
    @ResponseBody
    public String afterCancelAddLikeComment(@RequestBody CommentLike commentLike) {
        if (commentLike.getLikeStatus() == 0) {
            int n = service.afterCancelAddLikeComment(commentLike);
            int m = service.addRootLike(commentLike);
            if (n + m > 1) {
                return "OK";
            }
        }
        return "FAIL";
    }

    /**
     * 获取父级点赞的ClId
     *
     * @param commentLike
     * @return
     */
    @RequestMapping("/getCommentLike")
    @ResponseBody
    public CommentLike getCommentLike(@RequestBody CommentLike commentLike) {
        CommentLike like = dao.getClId(commentLike);
        if (like != null) {
            return like;
        }
        return null;
    }

    /**
     * 获取回复点赞信息
     *
     * @param rId
     * @param uId
     * @param rlId
     * @return 点赞状态
     */
    @PostMapping("/getReplyLikeInfo")
    @ResponseBody
    public int getReplyLikeInfo(int rId, int uId, int rlId) {

        ReplyLike res = service.getReplyLikeInfo(rId, uId, rlId);
        if (res != null) {
            return res.getLikeStatus();
        }
        return -1;
    }

    /**
     * 回复第一次点赞
     *
     * @param replyLike
     * @return
     */
    @PostMapping("/addReplyLike")
    @ResponseBody
    public ReplyLike addReplyLike(@RequestBody ReplyLike replyLike) {
        System.out.println("--------------回复点赞---------------");
        if (replyLike.getLikeStatus() == 1) {
            int n = service.addReplyLikeUser(replyLike);
            if (n > 0) {
                return service.addReplyLike(replyLike);
            }
        }
        return null;
    }

    /**
     * 取消回复点赞
     *
     * @param uId
     * @param rId
     * @return
     */
    @PostMapping("/cancelReplyLike")
    @ResponseBody
    public String cancelReplyLike(int uId, int rId) {
        int n = service.cancelReplyLikeUser(uId, rId);
        if (n > 0) {
            int m = service.cancelReplyLike(rId);
            if (m > 0) {
                return "OK";
            }
        }
        return "FAIL";
    }


    /**
     * 取消回复点赞，再点赞
     *
     * @param
     * @return
     */
    @RequestMapping("/afterCancelReplyLike")
    @ResponseBody
    public String afterCancelReplyLike(@RequestBody ReplyLike replyLike) {

        int n = service.afterCancelAddReplyLike(replyLike);
        if (n > 1) {
            return "OK";
        }
        return "FAIL";
    }
//---------------------------------------------------------------------------------------

    /**
     * 父级评论点赞（redis）
     *
     * @param cid
     * @param request
     * @return
     */
    @RequestMapping("/addFatherCommentLike")
    @ResponseBody
    public Msg addFatherCommentLike(int cid, HttpServletRequest request) {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        int res = likeService.like(user.getuID(), "commentlike", cid);
        if (res == 0) {
            return new Msg(200, res, "取消点赞");
        } else {
            return new Msg(200, res, "点赞成功");
        }
    }

    /**
     * 给子级评论点赞
     *
     * @param rid
     * @param req
     * @return
     */
    @RequestMapping("/addReplyLike1")
    @ResponseBody
    public Msg addReplyLike1(int rid, HttpServletRequest req) {
        Users user = (Users) req.getSession().getAttribute("loginUser");
        int res = likeService.like(user.getuID(), "replylike", rid);
        if (res == 0) {
            return new Msg(200, res, "取消点赞");
        } else {
            return new Msg(200, res, "点赞成功");
        }

    }

    /**
     * 获取评论点赞数量
     *
     * @param cid
     * @return
     */
    @RequestMapping("/getCommentLikeCount")
    @ResponseBody
    public Msg getCommentLikeCount(int cid) {
        long res = likeService.findEntityLikeCount("commentlike", cid);
        return new Msg(200, res, "查询成功");
    }

    /**
     * 获取子级评论点赞数量
     *
     * @param rid
     * @return
     */
    @RequestMapping("/getReplyLikeCount")
    @ResponseBody
    public Msg getReplyLikeCount(int rid) {
        long res = likeService.findEntityLikeCount("replylike", rid);
        return new Msg(200, res, "查询成功");
    }

    /***
     *  获取父级评论用户点赞情况
     * @param cid
     * @param request
     * @return
     */
    @RequestMapping("/getCommentLikeStatus")
    @ResponseBody
    public Msg getCommentLikeStatus(int cid, HttpServletRequest request) {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        int res = likeService.findEntityLikeStatus(user.getuID(), "commentlike", cid);
        if (res == 0) {
            return new Msg(200, res, "未点赞");
        } else {
            return new Msg(200, res, "已点赞");
        }
    }

    /**
     * 获取子级评论点赞情况
     *
     * @param
     * @param rid
     * @return
     */
    @RequestMapping("/getReplyLikeStatus")
    @ResponseBody
    public Msg getReplyLikeStatus(int rid, HttpServletRequest request) {
        Users user = (Users) request.getSession().getAttribute("loginUser");
        int res = likeService.findEntityLikeStatus(user.getuID(), "replylike", rid);
        if (res == 0) {
            return new Msg(200, res, "未点赞");
        } else {
            return new Msg(200, res, "已点赞");
        }
    }


    /**
     * 获取评论消息列表
     *
     * @param
     * @return
     */
    @RequestMapping("/getCommentInfo")
    @ResponseBody
    public List<CommentData> getCommentInfo(int hId) {

        List<Comment> fatherList = service.getCommentList(hId);
        List<CommentData> commentList = new ArrayList<>();
        System.out.println(commentList);
        for (int i = 0; i < fatherList.size(); i++) {
            System.out.println(fatherList.get(i).toString());
        }
        for (int i = 0; i < fatherList.size(); i++) {
            CommentData data = new CommentData();
            data.setComment(fatherList.get(i));
            List<Reply> subList = service.getCommentSubList(fatherList.get(i).getcId());
            data.setReplyList(subList);
            commentList.add(data);
        }
        return commentList;
    }

}
