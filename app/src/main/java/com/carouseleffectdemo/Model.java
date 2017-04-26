package com.carouseleffectdemo;

public class Model {

    int imagesource;
    String timezone;
    String time;

    public Model(int imageSource, String timezone, String time) {
        this.timezone = timezone;
        this.time = time;
        this.imagesource = imageSource;
    }

    public int getImagesource() {
        return imagesource;
    }

    public void setImagesource(int imagesource) {
        this.imagesource = imagesource;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
