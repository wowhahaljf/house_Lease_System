package com.house.house.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.house.house.entity.Complaint;
import com.house.house.entity.House;
import com.house.house.entity.Msg;
import com.house.house.entity.Users;
import com.house.house.service.IHouserService;
import com.house.house.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private IUserService service;
    @Autowired
    private IHouserService dao;

    @RequestMapping("/toUserSystem")
    public String toUserSystemPage() {
        return "customer";
    }

    @RequestMapping("/toUserRentalPage")
    public String toUserRentalPage() {
        return "myrental";
    }

    @RequestMapping("/welcome")
    public String toWelcomePage() {
        return "welcome";
    }

    @RequestMapping("/toUpdateHousePage")
    public String toUpdatePage(int hID, HttpServletRequest request) {
        House house = dao.findHouseDetailsById(hID);
        request.getSession().setAttribute("House", house);
        return "updatehouse";
    }

    /**
     * 用户修改密码
     *
     * @param id
     * @param newPwd
     * @param oldPwd
     * @return
     */
    @RequestMapping("/updateUserPwd")
    @ResponseBody
    public Msg updateUserPwd(String id, String newPwd, String oldPwd) {

        Users oldUser = new Users();
        oldUser.setuID(Integer.parseInt(id));
        //将新密码和旧密码加密处理
        String oPwd = DigestUtils.md5DigestAsHex(oldPwd.getBytes());
        String nPwd = DigestUtils.md5DigestAsHex(newPwd.getBytes());
        oldUser.setuPassword(oPwd);

        Users checkUser = service.checkOldPwd(oldUser);
        if (checkUser != null) {
            Users newUser = new Users();
            newUser.setuID(Integer.parseInt(id));
            newUser.setuPassword(nPwd);
            int n = service.updateUserPwd(newUser);
            if (n > 0) {
                return new Msg(200, newUser, "修改密码成功");
            }
        }
        return new Msg(400, oldUser, "修改失败");
    }

    /**
     * 检查用户名是否占用
     * @param uName
     */
    @GetMapping("/checkUName")
    @ResponseBody
    public Msg checkUName(@RequestParam("uName") String uName) {
        log.info("检查用户名---->" + uName);
        Users res = service.checkUName(uName);
        log.info("结果--->"+res);
        if (res != null) {
            return new Msg(200, 1, "用户名已存在！");
        }
        return new Msg(400, 0, "用户名可用！");
    }


    /**
     * 修改个人信息
     *
     * @param id
     * @param uName
     * @param uNickName
     * @param uPhoneNumber
     * @return
     */
    @RequestMapping("/updateInfo")
    @ResponseBody
    public Msg updateUserInfo(String id, String uName, String uNickName, String uPhoneNumber) {
        Users u = new Users();
        u.setuID(Integer.parseInt(id));
        u.setuName(uName);
        u.setuNickName(uNickName);
        u.setuPhoneNumber(uPhoneNumber);

        int res = service.updateInfo(u);
        if (res > 0) {
            return new Msg(200, u, "修改成功");
        }
        return new Msg(400, u, "修改失败");
    }

    /**
     * 提交投诉接口
     * @param hid
     * @param cplContent
     * @param request
     * @return
     */
    @PostMapping("/complainSubmit")
    @ResponseBody
    public Msg complainSubmit(int hid,String cplContent,HttpServletRequest request){
        HttpSession session = request.getSession();
        Users loginUser = (Users) session.getAttribute("loginUser");
        Complaint complaint = new Complaint(loginUser.getuID(), hid, cplContent);
        int res = service.complainSubmit(complaint);
        if(res>0){
            return new Msg(200,res,"提交成功");
        }
        return new Msg(400,res,"提交失败");
    }

    /**
     * 获取投诉用户
     * @param uid
     * @return
     */
    @GetMapping("/getComplaint")
    @ResponseBody
    public Msg getComplaint(int uid){
       return service.getComplaint(uid);
    }

    /**
     * 撤回投诉
     * @param cplId
     * @return
     */
    @PostMapping("/withdrawComplaint")
    @ResponseBody
    public Msg withdrawComplaint(@RequestParam int cplId){
        return service.withdrawComplaint(cplId);
    }

}
