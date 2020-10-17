package com.tgdz.fkptceshi.entity;

public class DataResult <T> {
    private T data;
    private String function;
    private String numOfHead;
    private String numOfHelmet;
    private String numOfNotHelmet;
    private String pic_data;
    private String srcpic_data;
    private String state;
    private String time_stamp;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getNumOfHead() {
        return numOfHead;
    }

    public void setNumOfHead(String numOfHead) {
        this.numOfHead = numOfHead;
    }

    public String getNumOfHelmet() {
        return numOfHelmet;
    }

    public void setNumOfHelmet(String numOfHelmet) {
        this.numOfHelmet = numOfHelmet;
    }

    public String getNumOfNotHelmet() {
        return numOfNotHelmet;
    }

    public void setNumOfNotHelmet(String numOfNotHelmet) {
        this.numOfNotHelmet = numOfNotHelmet;
    }

    public String getPic_data() {
        return pic_data;
    }

    public void setPic_data(String pic_data) {
        this.pic_data = pic_data;
    }

    public String getSrcpic_data() {
        return srcpic_data;
    }

    public void setSrcpic_data(String srcpic_data) {
        this.srcpic_data = srcpic_data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }


}
