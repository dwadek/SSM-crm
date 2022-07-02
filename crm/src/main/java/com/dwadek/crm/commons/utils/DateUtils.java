package com.dwadek.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 对指定的data对象进行格式化：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataStr = sdf.format(date);
        return dataStr;
    }
}
