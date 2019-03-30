/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artec.apps.facebook.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author imstu
 */
public class DateUtils {
    
    
    
    public static String convertMinutesToHours (Long t) {
        String hours = String.valueOf(t/60); //since both are ints, you get an int
        String minutes = ((t%60)<10)?"0"+String.valueOf(t%60):String.valueOf(t%60);
        return hours+":"+minutes+" HRS";
    }
    
    public static String getDayNumberESGT(Long day) {
        switch(day.intValue()) {
            case 1: return "Lunes";
            case 2: return "Martes";
            case 3: return "Miercoles";
            case 4: return "Jueves";
            case 5: return "Viernes";
            case 6: return "Sábado";
            case 0: return "Domingo";
        }
        return "NO DEFINIDO";
    }
    
    public static String GuatemalaTime(String facebookTime, String dateFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        Date date = format.parse(facebookTime);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        formatter.setCalendar(calendar);
        formatter.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
        return formatter.format(calendar.getTime());
    }
    
    
    
    public static boolean DateAfter(Date createdDate, String afterDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date sdate = formatter.parse(afterDate);
        Calendar c = Calendar.getInstance();
        c.setTime(sdate);
        c.add(Calendar.DATE, -1);
        return createdDate.after(sdate) ;        
    }
    
    public static boolean DateBefore(Date createdDate, String beforeDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date sdate = formatter.parse(beforeDate);
        Calendar c = Calendar.getInstance();
        c.setTime(sdate);
        c.add(Calendar.DATE, 1);
        return createdDate.before(sdate) ;        
    }
    
    public static String UNIXTime (String time) throws ParseException {
        final String OLD_FORMAT = "dd/MM/yyyy HH:mm:ss";
        final String NEW_FORMAT = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(time);
        sdf.applyPattern(NEW_FORMAT);
        DateFormat dfm = new SimpleDateFormat(NEW_FORMAT);
        return String.valueOf((dfm.parse(sdf.format(d)).getTime())/1000);
    }
    
}