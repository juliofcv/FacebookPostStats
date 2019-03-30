/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artec.apps.facebook.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

/**
 *
 * @author Julio Chinchilla
 */
public class Time {
    
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String TIMESERVER = "time.windows.com";
    

    public static String getTime(DateFormat df) {
        try {
            return getInetTime(df);
        } catch (Exception ex) {
            return new Date().toString();
        }
    }


    
    public static String getInetTime(DateFormat df) throws UnknownHostException, IOException {
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(TIMESERVER);
        TimeInfo timeInfo = timeClient.getTime(inetAddress);
        long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
        Date time = new Date(returnTime);
        return df.format(time);
    }
    
}