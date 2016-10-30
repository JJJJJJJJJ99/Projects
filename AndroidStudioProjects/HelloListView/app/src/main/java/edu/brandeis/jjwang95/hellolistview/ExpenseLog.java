package edu.brandeis.jjwang95.hellolistview;


import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by WangJingjing on 10/20/16.
 */

public class ExpenseLog implements Serializable{
    private String time = "";
    private String amount = "";
    private String note = "";

    public void setTime(){
        SimpleDateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        //timeFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String time = timeFormat.format(new Date());

//        Calendar c = Calendar.getInstance();
//        c.setTimeZone();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int date = c.get(Calendar.DATE);
//        int hour = c.get(Calendar.HOUR);
//        int minute = c.get(Calendar.MINUTE);
//
//        // Set time
//        final String inputTime = month + "/" + date + "/" + year + " " + hour + ":" + minute;
//        Log.d("Input time", inputTime);
        this.time = "Time: " + time;
    }

    public void setAmount(String content){
        this.amount = "Amount: " + content;
    }

    public void setNote(String content){
        this.note = "Note: "+content;
    }

    public String getTime(){
        if (this.time == ""){
            return "Not created yet";
        } else {
            return time;
        }
    }
    public String getAmount(){
        if (this.amount == ""){
            return "Not created yet";
        } else {
        return amount;}
    }
    public String getNote() {
        if (this.note == "") {
            return "Not created yet";
        } else {
            return note;
        }
    }
}
