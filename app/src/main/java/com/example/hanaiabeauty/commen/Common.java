package com.example.hanaiabeauty.commen;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateFormat;

import com.example.hanaiabeauty.model.Catogray;
import com.example.hanaiabeauty.model.UserDetail;

import java.util.Calendar;
import java.util.Locale;


public class Common {
    public static UserDetail currentUser;


    public static Catogray catograycart;

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;



public final static String DEELETE="delete";
    public final static String USERKEY="name";
    public final static String USER_PASS="pass";
    public static final int PICK_IMAGE = 71;


    public static boolean isNetworkOnline(Context context) {
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;

    }
    public static String convertcode(String statues) {

        if (statues.equals("0"))
            return "Placed";
        else if (statues.equals("1"))
            return "on my way";
        else
            return "shipped";
    }

    public static String getDate(long time){

        Calendar calendar=Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        StringBuilder date=new StringBuilder(DateFormat.format("dd-MM-yyyy HH:mm",
                calendar
        ).toString());
        return date.toString();
    }

}
