package com.house.house.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.house.house.entity.Admin;
import com.house.house.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {
    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在拦截点（Controller方法处理之前）执行拦截 若返回的是false则中断执行 反之亦然
        response.setCharacterEncoding("utf-8");
//        String url =request.getRequestURL().toString();

        String uri=request.getRequestURI();
        log.info("拦截--------->"+uri);
        String []uris=new String[]{
                "/HomePage01",
                "/getCommentInfo",
                "/getReplyLikeInfo",
                "/login1",
                "/toIndexPageInfo",
                "/toDetailsPage01",
                "/adminAccess",
                "/regist",
                "/findHousePriceOrderByDesc",
                "/findHousrOrderByDesc",
                "/findHousePriceOrderByAsc",
                "/search**"
        };

        if(check(uri,uris)){
            log.info("放行------------>"+uri);
           return true;

        }

//        if(url.indexOf("HomePage01")>=0 ||url.indexOf("getCommentInfo")>=0||url.indexOf("getReplyLikeInfo")>=0||url.indexOf("login1")>=0){
//            return true;
//        }

        HttpSession session = request.getSession();
        Users u= (Users) session.getAttribute("loginUser");
        Admin admin = (Admin) session.getAttribute("Admin");


        if(u!=null||admin!=null){
            if(u!=null){
                System.out.println("登录的账号---->"+u);
            }else{
                System.out.println("登录的账号---->"+admin);
            }
            return true;
        }

        //拦截器拦截后，发送json返回给前端当前状态
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;
        try{
            System.out.println("未登录！！！！！！！！！！！！！");
            JSONObject res = new JSONObject();
            res.put("code","0");
            res.put("msg","NoLogin");
            out = response.getWriter();
            out.append(res.toString());
            return false;
        } catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
//        request.getRequestDispatcher("/")

    }

    /**
     * 遍历不用拦截的uri，放行对应的uri
     * @param uri
     * @param uris
     * @return
     */
    private boolean check(String uri, String[] uris) {
        for (String url :
                uris) {
            if (PATH_MATCHER.match(url, uri)) {
                return true;
            }

        }
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
