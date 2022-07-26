package com.dwadek.crm.workbench.web.controller;

import com.dwadek.crm.commons.contants.Contants;
import com.dwadek.crm.commons.utils.DateUtils;
import com.dwadek.crm.commons.utils.UUIDUtils;
import com.dwadek.crm.settings.pojo.ReturnObject;
import com.dwadek.crm.settings.pojo.User;
import com.dwadek.crm.settings.service.UserService;
import com.dwadek.crm.workbench.pojo.Activity;
import com.dwadek.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询所有用户
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList",userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(Activity activity, HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        //封装数据
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        try{
            //调用service层方法，保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);
            if(ret > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
                returnObject.setMessage("系统忙，请稍后重试。。。。。");
            }
        }catch (Exception e){
            e.printStackTrace();

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
            returnObject.setMessage("系统忙，请稍后重试。。。。。");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    @ResponseBody
    public Object deleteActivityByIds(String[] id){
        ReturnObject returnObject = new ReturnObject();
        try{
            //调用service方法，删除市场活动
            int  ret = activityService.deleteActivityByIds(id);
            if(ret > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
                returnObject.setMessage("系统忙，请稍后重试。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
            returnObject.setMessage("系统忙，请稍后重试。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(String id){
        //调用service查询市场活动
        Activity activity = activityService.queryActivityById(id);
        return activity;
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    @ResponseBody
    public Object saveEditActivity(Activity activity,HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        activity.setEditTime(DateUtils.formatDateTime(new Date()));
        activity.setEditBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，保存修改的市场活动
            int ret = activityService.saveActivity(activity);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
                returnObject.setMessage("系统忙，请稍后。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAILURE);
            returnObject.setMessage("系统忙，请稍后。。。");
        }
        return returnObject;
    }
}
