package com.dwadek.crm.settings.web.controller;

import com.dwadek.crm.commons.contants.Contants;
import com.dwadek.crm.commons.utils.DateUtils;
import com.dwadek.crm.settings.pojo.ReturnObject;
import com.dwadek.crm.settings.pojo.User;
import com.dwadek.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * url要和controller方法处理请求之后，响应信息返回的页面的资源目录保持一致
     * @return
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        //请求转发到登录页面
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpSession session){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用service方法，查询用户
        User user =  userService.queryUserByLoginActAndPwd(map);
        //根据查询结果，生成响应信息
        ReturnObject returnObject = new ReturnObject();
        if(user == null){
            //登陆失败，用户名或密码错误
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
            returnObject.setMessage("用户名或密码错误");
        }else {
            if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime())>0){
                //登陆失败，账号已过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
                returnObject.setMessage("账号已过期");
            }else if ("0".equals(user.getLockState())){
                //登陆失败，状态被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
                returnObject.setMessage("账号被锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //登陆失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
                returnObject.setMessage("ip受限");
            }else {
                //登录成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

                //把user保存到session中
                session.setAttribute(Contants.SESSION_USER,user);
            }
        }
        return returnObject;

    }
}