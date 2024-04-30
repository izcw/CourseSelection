package com.Tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeHelper {
    public static String GetCurrentTimeToString(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(calendar.getTime());

    }
}
