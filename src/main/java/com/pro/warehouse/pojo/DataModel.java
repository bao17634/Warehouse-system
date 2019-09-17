package com.pro.warehouse.pojo;


import java.io.Serializable;

public class DataModel implements Serializable {
    private String date;
    private String size;
    public DataModel(String date, String size){
        this.size = size;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
