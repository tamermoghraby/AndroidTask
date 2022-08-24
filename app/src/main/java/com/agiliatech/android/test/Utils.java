package com.agiliatech.android.test;

import com.agiliatech.android.test.ModelResponse.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {

    public static List<Session> getFirstDaySessions(List<Session> list){
        //Getting the day of first sessions
        int day = getDayFromMillisec(list.get(0).getStart_time_ms());


        for(int i=0; i < list.size(); i++){
            //Initializing variables
            int testDay = getDayFromMillisec(list.get(i).getStart_time_ms());

            //Checking if session date matches the first one
            if(testDay != day){

                //Add Session to our List
                list.remove(i);

            }
        }

        return list;

    }

    public static int getDayFromMillisec(long milliSec){

        DateFormat day = new SimpleDateFormat("dd");
        Date date = new Date(milliSec);
        String s = day.format(date);
        int i = Integer.parseInt(s);
        return i;

    }

    public static String getTimeFromMillisec(long milliSec){

        DateFormat time = new SimpleDateFormat("HH:mm");
        Date date = new Date(milliSec);
        String s = time.format(date);
        return s;

    }

}
