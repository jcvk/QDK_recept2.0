package com.example.qdk.qdk_recept20.Class;

/**
 * Created by lenovo on 2017/4/4.
 */

public class RecordClass {

    private int id;
    private String content;
    private String time;

    public RecordClass(int id, String content, String time){
        this.id=id;
        this.content=content;
        this.time=time;
    }

    public String getContent(){
        return content;
    }

    public String getTime(){
        return time;
    }

    public int getId(){
        return id;
    }
}
