package com.example.jason.myapplication;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Andrew on 3/22/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class restaurant {
    private String name;
    private String address;
    private String phone;
    private TimeLog log;
    private int avg;
    private String image;

    public restaurant() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }
    //public restaurant(String name, String address, String phone, double avg) {
    public restaurant(String _phone, String _name, String _address, int _avg, TimeLog _log, String _image) {
        name = _name;
        address = _address;
        phone = _phone;
        avg = _avg;
        log = _log;
        image = _image;
    }
    public String getAddress() {return address;}
    public String getPhone() {return phone;}
    public String getName(){return name;}
    public int getAvg(){return avg;}
    public String getImage(){return image;}

    //public TimeLog getLog() { return log; }
}
