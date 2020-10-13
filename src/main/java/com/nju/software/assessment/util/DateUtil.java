package com.nju.software.assessment.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Timestamp format(String dateStr) throws ParseException {
        dateStr=dateStr.replace("T"," ").replace("Z","");
        return Timestamp.valueOf(dateStr);
    }
    public static String formatStr(String dateStr) throws ParseException {
        return dateStr.replace("T"," ").replace("Z","").substring(0,19);

    }
    public static String dateToStrLong(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
  }
  public static Date StringToDate(String dateStr) throws ParseException {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = formatter.parse(dateStr);
      return date;
  }
}
