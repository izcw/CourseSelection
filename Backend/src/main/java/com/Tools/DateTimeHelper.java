package com.Tools;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class DateTimeHelper {
    public static String GetCurrentTimeToString(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(calendar.getTime());
    }

    //  返回当前年份的后两位数
//    public static String LasttwoYear() {
//        LocalDate currentDate = LocalDate.now();
//        // 获取当前年份的后两位数
//        int year = currentDate.getYear() % 100;
//        String yearStr = String.format("%02d", year);
//        return yearStr;
//    }
}
