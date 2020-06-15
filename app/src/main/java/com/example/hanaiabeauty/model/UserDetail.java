package com.example.hanaiabeauty.model;

public class UserDetail {

    private String name;
    private String pasword;
    private String  phone;
    private String scorecod;


    public UserDetail(String name, String pasword, String phone, String scorecod) {
        this.name = name;
        this.pasword = pasword;
        this.phone = phone;
        this.scorecod = scorecod;
    }

    public UserDetail() {
    }

    public String getScorecod() {
        return scorecod;
    }

    public void setScorecod(String scorecod) {
        this.scorecod = scorecod;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
}




