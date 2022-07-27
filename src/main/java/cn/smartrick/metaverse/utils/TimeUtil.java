package cn.smartrick.metaverse.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * [ 时间工具 ]
 *
 * @author yandanyang
 * @version 1.0
 * @company 1024lab.net
 * @copyright (c) 2018 1024lab.netInc. All rights reserved.
 * @date 2019/5/5 0005 下午 15:34
 * @since JDK1.8
 */
public class TimeUtil {
    /**
     * 对比两个时间的天数差，
     * > 0 :表示时间一大于时间二多少天
     * < 0 :表示时间一小于时间二多少天
     * = 0 :两个时间为同一天
     *
     * @param one
     * @param two
     * @return
     */
    public static int dayCompare(Date one, Date two) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(one);
        int lastDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(two);
        int toDay = calendar.get(Calendar.DAY_OF_MONTH);
        return lastDay - toDay;
    }

    public static int todayCompare(Date lastTime) {
        return dayCompare(lastTime, new Date());
    }
}
