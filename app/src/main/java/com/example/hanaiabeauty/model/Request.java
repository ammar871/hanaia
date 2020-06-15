package com.example.hanaiabeauty.model;

import java.util.List;

public class Request {
    private String phone;
    private String name;
    private String addresse;
    private String total;
    private String statues;
    private String comment;
    private List<Catogray> foods;

    public Request(String phone, String name, String addresse, String total, String statues, String comment, List<Catogray> foods) {
        this.phone = phone;
        this.name = name;
        this.addresse = addresse;
        this.total = total;
        this.statues = statues;
        this.comment = comment;
        this.foods = foods;
    }

    public Request() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
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

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Catogray> getFoods() {
        return foods;
    }

    public void setFoods(List<Catogray> foods) {
        this.foods = foods;
    }
}
