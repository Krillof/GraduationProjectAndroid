package com.example.graduationprojectandroid.network.endpoints;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("str")
    @Expose
    private String str;
    @SerializedName("time")
    @Expose
    private String time;

    public String getId() {
        return id;
    }

    public String getStr() {
        return str;
    }

    public String getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
