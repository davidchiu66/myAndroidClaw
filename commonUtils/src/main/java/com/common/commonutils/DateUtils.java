package com.common.commonutils;

import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 解析格式:
 * yyyy表示年
 * MM(大写M)表示月
 * dd表示天
 * HH表示小时24小时制，小写的话是12小时制
 * mm，小写，表示分钟
 * ss表示秒
 */
public class DateUtils {
    public static long getNowTimeLong() {
        return System.currentTimeMillis();
    }

    public static String getNowTime() {
        String chinese = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return chinese;
    }

    public static String getCurrentTime(String format) {
        long time = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }

    //年月日 转换为 时间戳
    public static long time2Long(String time) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return new Date().getTime();
        }

    }

    public static long time2Long_s(String time) {
        Date date = null;
        try {
            date = new SimpleDateFormat("HH:mm").parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return new Date().getTime();
        }

    }

    //年月日 转换为 时间戳
    public static long time2Long(String time,String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return new Date().getTime();
        }

    }


    //yyyy-MM-dd 转换为 年月日
    public static String time2Chinese(String time) {
        Date date = null;
        String chinese;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
            chinese = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        } catch (Exception e) {
            chinese = time;
        }

        return chinese;
    }

    //yyyy-MM-dd 转换为 yyyy/MM/dd
    public static String time2II(String time) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String chinese = new SimpleDateFormat("yyyy/MM/dd").format(date);
        return chinese;
    }

    //yyyy-MM-dd 转换为yyyy.MM.dd
    public static String time2Dot(String time) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (date != null) {
            return new SimpleDateFormat("yyyy.MM.dd").format(date);
        } else {
            return time;
        }
    }


    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginTime = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginTime);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endTime = null;
        try {
            endTime = dft.parse(dft.format(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dft.format(endTime);
    }

    public static String getDatePoor(long startTime, long endTime) {
        long nh = 1000 * 60 * 60;
        long poor = endTime - startTime;
        if (poor < 0) {
            poor = poor + 1000 * 60 * 60 * 24;
        }
        return String.valueOf(new BigDecimal(poor).divide(new BigDecimal(nh), 1, BigDecimal.ROUND_HALF_UP));
    }


    /**
     * 时间格式化
     *
     * @param textView    时间控件
     * @param millisecond 总时间 毫秒
     */
    public static String updataTimeFormat(TextView textView, int millisecond) {
        //将毫秒转换为秒
        int second = millisecond / 1000;
        //计算小时
        int hh = second / 3600;
        //计算分钟
        int mm = second % 3600 / 60;
        //计算秒
        int ss = second % 60;
        //判断时间单位的位数
        String str = null;
        if (hh != 0) {//表示时间单位为三位
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format("%02d:%02d", mm, ss);
        }
        //将时间赋值给控件
        if (textView != null) {
            textView.setText(str);
        }
        return str;
    }

    //根据年月日 算 周几
    public static String getWeek(String pTime) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "周日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "周六";
        }
        return Week;
    }

    /**
     * 根据年月日 算 周几
     *
     * @return 星期
     */
    public static String getWeek2(String pTime) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "星期日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "星期一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "星期二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "星期三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "星期四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "星期五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "星期六";
        }
        return Week;
    }

    /**
     * 将日期分别转换为年月日时分秒
     *
     * @param time
     * @return
     */
    public static int[] convertToCalendar(final String time) {
        int[] timeArray = new int[6];
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(time);
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(date);
            timeArray[0] = calendar.get(Calendar.YEAR);
            /*月份是从0开始*/
            timeArray[1] = calendar.get(Calendar.MONTH) + 1;
            timeArray[2] = calendar.get(Calendar.DAY_OF_MONTH);
            timeArray[3] = calendar.get(Calendar.HOUR_OF_DAY);
            timeArray[4] = calendar.get(Calendar.MINUTE);
            timeArray[5] = calendar.get(Calendar.SECOND);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeArray;
    }


    public static int[] forwardCalendar(final String time) {
        int[] timeArray = new int[3];
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(time);
            calendar.setTime(date);
            timeArray[0] = calendar.get(Calendar.YEAR) - 1;
            /*月份是从0开始*/
            timeArray[1] = calendar.get(Calendar.MONTH);
            if (timeArray[1] == 0) {
                timeArray[1] = 12;
            }
            timeArray[2] = calendar.get(Calendar.DAY_OF_MONTH) - 1;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeArray;
    }

    public static int[] convertToCalendar(final String time, String pattern) {
        int[] timeArray = new int[6];
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(time);
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(date);
            timeArray[0] = calendar.get(Calendar.YEAR);
            /*月份是从0开始*/
            timeArray[1] = calendar.get(Calendar.MONTH) + 1;
            timeArray[2] = calendar.get(Calendar.DAY_OF_MONTH);
            timeArray[3] = calendar.get(Calendar.HOUR_OF_DAY);
            timeArray[4] = calendar.get(Calendar.MINUTE);
            timeArray[5] = calendar.get(Calendar.SECOND);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeArray;
    }

    /**
     * 时间差
     *
     * @return Time difference
     */
    public static Long timeDifference(String time2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /* 当前系统时间*/
        Date date = new Date(System.currentTimeMillis());
        date.setSeconds(0);
        String time1 = simpleDateFormat.format(date);

        /*计算时间差*/
        Date begin = null;
        try {
            begin = simpleDateFormat.parse(time1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date end = null;
        try {
            end = simpleDateFormat.parse(time2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (end == null || begin == null) return 1000L;
        long diff = end.getTime() - begin.getTime();
//        /*计算天数*/
//        long days = diff / (1000 * 60 * 60 * 24);
//        /*计算小时*/
//        long hours = (diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        /*计算分钟*/
//        long minutes = (diff % (1000 * 60 * 60)) / (1000 * 60);
        /*计算秒*/
//        long seconds = (diff % (1000 * 60)) / 1000;

        /*计算分钟*/
        long minutes = diff / 1000 / 60;
        Logger.t(ConstantUtils.TAG).d("时间差：" + diff);
        Logger.t(ConstantUtils.TAG).d("时间差分钟数：" + minutes);
        return minutes;
    }

    public static String getUtcTime(){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.format(calendar.getTime());
    }

}
