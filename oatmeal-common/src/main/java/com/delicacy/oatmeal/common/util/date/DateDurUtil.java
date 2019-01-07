package com.delicacy.oatmeal.common.util.date;

import org.apache.commons.lang3.Validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间区间计算工具类
 *
 * @author zyt
 * @create 2018-01-25 11:45
 **/
public class DateDurUtil {
    private final static int SECOND_UNIT = 1000;
    private final static int MINUTE_UNIT = 60 * 1000;
    private final static int HOUR_UNIT = 60 * 60 * 1000;

    /**
     * 计算分钟差
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Long calculateSecond(Date beginTime, Date endTime) {
        return calculateTime(beginTime, endTime, SECOND_UNIT);
    }


    /**
     * 计算分钟差
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Long calculateMinute(Date beginTime, Date endTime) {
        return calculateTime(beginTime, endTime, MINUTE_UNIT);
    }


    /**
     * 计算分钟差
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Long calculateHour(Date beginTime, Date endTime) {
        return calculateTime(beginTime, endTime, HOUR_UNIT);
    }

    /**
     * 计算时间差
     *
     * @param beginTime
     * @param endTime
     * @param unit
     * @return
     */
    private static Long calculateTime(Date beginTime, Date endTime, int unit) {
        Validate.notNull(beginTime);
        Validate.notNull(endTime);
        long time1 = beginTime.getTime();
        long time2 = endTime.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        Long difference = diff / unit;
        return difference;
    }

	public static void main(String[] args)  {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(" 2018-01-25 11:05:36");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long time = calculateTime(new Date(), date, HOUR_UNIT);
		System.out.println(time);
	}


}