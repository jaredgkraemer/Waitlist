package com.example.jason.myapplication;

/**
 * Created by Andrew on 4/17/2016.
 */
public class TimeLog {


    private String time;
    private int selection;

    public TimeLog(){}

    public TimeLog(String time, int selection){
        this.time = time;
        this.selection = selection;
    }

    public String getTime()
    {
        return time;
    }

    public int getSelection()
    {
        return selection;
    }

}

