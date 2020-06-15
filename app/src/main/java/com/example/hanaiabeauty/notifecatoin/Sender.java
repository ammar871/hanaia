package com.example.hanaiabeauty.notifecatoin;

import com.google.gson.annotations.SerializedName;

public class Sender {

    private String to;


    private Data data;

    public Sender() {
    }

    public Sender(String to, Data data) {
        this.to = to;
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
