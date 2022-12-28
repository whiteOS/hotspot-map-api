package com.entire.hotspotmap.system.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtil {
    public final static String yyyyMMdd = "yyyy-MM-dd";
    public final static String yyyyMM = "yyyy-MM";
    public final static String HHmm = "HH:mm";

    public static Date getNowDate() {
        return new Date(System.currentTimeMillis());
    }

    public static String getNowDateStr() {
        return getNowDate().toString();
    }

    public static String getDateStrPatter(java.util.Date date, String patter) {
        SimpleDateFormat fmt = null;
        try {
            fmt = new SimpleDateFormat(patter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fmt != null ? fmt.format(date) : null;
    }

    public static String getDateStr(Date date) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(yyyyMMdd);
            return df.format(date);
        }
        return "";
    }

    public static String getDateStr(java.util.Date date) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(yyyyMMdd);
            return df.format(date);
        }
        return "";
    }

    public static Date getDate(String str) {
        java.util.Date date = null;
        try {
            date = new SimpleDateFormat(yyyyMMdd).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(date != null ? date.getTime() : 0);
    }

    /**
     * 指定日期的, 第一天和最后一天
     *
     * @param dataStr
     * @return
     */
    public static String[] getFirstAndLastOfWeek(String dataStr) {
        try {
            Calendar cal = Calendar.getInstance();

            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dataStr));

            int d;
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                d = -6;
            } else {
                d = 2 - cal.get(Calendar.DAY_OF_WEEK);
            }
            cal.add(Calendar.DAY_OF_WEEK, d);
            // 所在周开始日期
            String data1 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            cal.add(Calendar.DAY_OF_WEEK, 6);
            // 所在周结束日期
            String data2 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            return new String[]{data1, data2};
        } catch (Exception e) {
//            throw new ServiceException("获取对应时间所在周的第一天失败");
            return null;
        }

    }

    /**
     * 判断2个日期
     *
     * @param DATE1 在前面   1 , 在后面-1 ,相等 0
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date dt1 = df.parse(DATE1);
            java.util.Date dt2 = df.parse(DATE2);
            if (dt1.getTime() < dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() > dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 根据传入的年月周 返回 日期对应的天
     *
     * @param year
     * @param month
     * @param week  一周,二周,三周,四周,五周,每周
     * @return
     */
    public static List<String> getdays(Integer year, Integer month, String week) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        String startDate;
        String endDate;
        String[] weeks = week.split(",");
        String[] firstAndLastOfWeek;

        String curDate = getDateStr(getNowDate());

        List<String> dateList = new ArrayList<String>();
        dateList.clear();

        if ("每周".equals(week)) {
            cal.set(Calendar.DATE, 1);
            firstAndLastOfWeek = getFirstAndLastOfWeek(getDateStr(cal.getTime()));
            startDate = firstAndLastOfWeek[0];
            startDate = compare_date(startDate, curDate) == 1 ? curDate : startDate;

            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            firstAndLastOfWeek = getFirstAndLastOfWeek(getDateStr(cal.getTime()));
            endDate = firstAndLastOfWeek[1];

            return getBetweenDates(startDate, endDate);
        } else {
            for (String weekstr : weeks) {
                switch (weekstr) {
                    case "一周":
                        cal.set(Calendar.DATE, 1);
                        firstAndLastOfWeek = getFirstAndLastOfWeek(getDateStr(cal.getTime()));
                        startDate = compare_date(firstAndLastOfWeek[0], curDate) == 1 ? curDate : firstAndLastOfWeek[0];
                        endDate = firstAndLastOfWeek[1];
                        dateList.addAll(getBetweenDates(startDate, endDate));
                        break;
                    case "二周":
                        cal.set(Calendar.DATE, 1);
                        cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                        firstAndLastOfWeek = getFirstAndLastOfWeek(getDateStr(cal.getTime()));
                        startDate = compare_date(firstAndLastOfWeek[0], curDate) == 1 ? curDate : firstAndLastOfWeek[0];
                        endDate = firstAndLastOfWeek[1];
                        dateList.addAll(getBetweenDates(startDate, endDate));
                        break;
                    case "三周":
                        cal.set(Calendar.DATE, 1);
                        cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
                        firstAndLastOfWeek = getFirstAndLastOfWeek(getDateStr(cal.getTime()));
                        startDate = compare_date(firstAndLastOfWeek[0], curDate) == 1 ? curDate : firstAndLastOfWeek[0];
                        endDate = firstAndLastOfWeek[1];
                        dateList.addAll(getBetweenDates(startDate, endDate));
                        break;
                    case "四周":
                        cal.set(Calendar.DATE, 1);
                        cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, 3);
                        firstAndLastOfWeek = getFirstAndLastOfWeek(getDateStr(cal.getTime()));
                        startDate = compare_date(firstAndLastOfWeek[0], curDate) == 1 ? curDate : firstAndLastOfWeek[0];
                        endDate = firstAndLastOfWeek[1];
                        dateList.addAll(getBetweenDates(startDate, endDate));
                        break;
                    case "五周":
                        cal.set(Calendar.DATE, 1);
                        cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, 4);
                        firstAndLastOfWeek = getFirstAndLastOfWeek(getDateStr(cal.getTime()));
                        startDate = compare_date(firstAndLastOfWeek[0], curDate) == 1 ? curDate : firstAndLastOfWeek[0];
                        endDate = firstAndLastOfWeek[1];
                        dateList.addAll(getBetweenDates(startDate, endDate));
                        break;
                    case "六周":
                        cal.set(Calendar.DATE, 1);
                        cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, 5);
                        firstAndLastOfWeek = getFirstAndLastOfWeek(getDateStr(cal.getTime()));
                        startDate = compare_date(firstAndLastOfWeek[0], curDate) == 1 ? curDate : firstAndLastOfWeek[0];
                        endDate = firstAndLastOfWeek[1];
                        dateList.addAll(getBetweenDates(startDate, endDate));
                        break;
                }
            }
        }

        return dateList;

    }


    /**
     * 获取两个日期之间的所有日期集合
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getBetweenDates(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date start = dateFormat.parse(startTime);
            java.util.Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }


    public static String addMonth(String str, Integer month) {
        Date date = getDate(str);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return getDateStrPatter(c.getTime(), yyyyMMdd);
    }


    public static void main(String[] args) {

        System.out.println(getdays(2022, 5, "每周"));
		 /*System.out.println(getWeekStr("2022-02-28"));
		 System.out.println(diffStr("2022-02-28",2));*/

//        List<String> lastOfWeekdates = new ArrayList<String>();
//        lastOfWeekdates.add("2022-02-28");
//        String last_date = lastOfWeekdates.get(lastOfWeekdates.size()-1);
//        if(!DateUtil.getWeekStr(last_date).equals("周日")) {
//            String week_6 = DateUtil.diffStr(last_date,2);
//            String week_7 = DateUtil.diffStr(last_date,1);
//            lastOfWeekdates.add(0, week_7);
//            lastOfWeekdates.add(0, week_6);
//        }
//
//        for (String str : lastOfWeekdates) {
//            System.out.println(str);
//        }
    }

    public static String diffStr(String datestr, int diff) {
        SimpleDateFormat df = new SimpleDateFormat(yyyyMMdd);
        java.util.Date date = new java.util.Date();
        try {
            long dif = df.parse(datestr).getTime() - ((86400 * 1000) * diff);
            date.setTime(dif);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return df.format(date);
    }

    public static String getWeekStr(String date) {

        SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

        Calendar cal = Calendar.getInstance();
        try {

            java.util.Date tmpDate = format.parse(date);
            cal.setTime(tmpDate);

        } catch (Exception e) {

            e.printStackTrace();

        }

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。

        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }


    /**
     * 获取当前的时间对应的月, 的最后一个星期的日期 ,如果最后一个星期存在跨月,跨月的那些天不获取进来
     *
     * @return
     */
    public static List<String> getLastWeekDates() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        //calendar.add(Calendar.MONTH, 0);

        //int weekFirst = calendar.getFirstDayOfWeek();
        // System.out.println("firstDD:"+weekFirst);
        //设置为最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("当前月份最后一天：" + getDateStrPatter(calendar.getTime(), yyyyMMdd));

        // System.out.println("firstDD:"+calendar.getFirstDayOfWeek());
        //最近这一周的起始日期【将DAY_OF_WEEK设置为周的第一天，则日期也会发生变化，变为对应天数】
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        int m = calendar.get(Calendar.MONTH);
        List<String> dates = new ArrayList<String>();
        for (int i = 1; i <= 7; i++) {
            calendar.add(calendar.DATE, 1);
            java.util.Date d = calendar.getTime();
            //加了天数后的月份,要判断还是不是在当月内,是的话才能返回,跨越了的日期不要
            int _m = calendar.get(Calendar.MONTH);
            if (m == _m) {
                dates.add(getDateStrPatter(calendar.getTime(), yyyyMMdd));
            }
        }
        return dates;
    }

    public static Boolean isCurYear(String year) {
        String date = getNowDateStr();
        return date.split("-")[0].equals(year);
    }

    public static String date(Timestamp time) {
        DateFormat df = new SimpleDateFormat(yyyyMMdd);
        return df.format(time);
    }

    public static String hour(Timestamp time) {
        DateFormat df = new SimpleDateFormat(HHmm);
        return df.format(time);
    }

}

