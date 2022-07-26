package com.dwadek.crm.workbench.service;

import com.dwadek.crm.workbench.pojo.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditonForPage(Map<String,Object> map);

    int queryCountOfActivityByCondition(Map<String,Object> map);

    int deleteActivityByIds(String[] ids);

    Activity queryActivityById(String id);

    int saveActivity(Activity activity);
}
