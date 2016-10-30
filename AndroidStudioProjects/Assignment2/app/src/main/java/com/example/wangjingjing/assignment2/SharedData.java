package com.example.wangjingjing.assignment2;

/**
 * Created by WangJingjing on 9/30/16.
 */

public class SharedData {
    private String about = "not created";

    private static final SharedData holder = new SharedData();

    public void storeData(String content){
        this.about = content;
    }

    public String getSharedData(){
        return about;
    }
    public static SharedData getInstance() {
        return holder;
    }
}
