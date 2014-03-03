package com.flipkart.automobile.tests.util;

import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: shikha.agrawal
 * Date: 30/12/13
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogParser {

    @SuppressWarnings("deprecation")
    public static void parseFile() throws IOException,Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./log/events.log.2013-12-29")));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./log/eventLog.log")));
        String line, temp, temp1;
        String timeStampPattern = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9].[0-9][0-9][0-9]";
        String startDate = "21:34:55.034";
        String endDate ="22:46:46.736";
        java.util.Date date;
        java.sql.Timestamp start_Date,end_Date,temp_Date;
        while((line = br.readLine())!= null) {
            temp1 = line.substring(0,12);
            System.out.println(temp1);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
            date = sdf.parse(startDate);
            start_Date = new java.sql.Timestamp(date.getTime());
            date = sdf.parse(endDate);
            end_Date = new java.sql.Timestamp(date.getTime());
            date = sdf.parse(temp1);
            temp_Date  = new java.sql.Timestamp(date.getTime());
            System.out.println(end_Date.toString());
            if(temp_Date.compareTo(start_Date)>0 && temp_Date.compareTo(end_Date)<0)
            {
                bw.write(line + "\n");
            }
        }
        br.close();
        bw.close();
    }


    @SuppressWarnings("deprecation")
    public static void getUrls() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./log/eventLog.log")));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./log/urls.txt")));
        String line,temp;
        while((line = br.readLine())!= null) {
            temp = line.substring(line.indexOf("Uri = ") + 5) ;
            temp = temp.substring(0,temp.indexOf("|")).trim();
            bw.write(URLDecoder.decode(URLDecoder.decode(temp))+"\n");
        }
        bw.close();
        br.close();
    }


}
