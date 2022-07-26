package com.dwadek.crm.workbench.mapper;


import com.dwadek.crm.workbench.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


public interface ActivityMapper {

    int insertActivity(Activity activity);

    List<Activity> selectActivityByConditionForPage(Map<String, Object> map);

    int selectCountOfActivityByCondition(Map<String, Object> map);

    int deleteActivityByIds(String[] ids);

    Activity selectActivityById(String id);

    int updateActivity(Activity activity);
}
