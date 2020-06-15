package com.example.hanaiabeauty.model;

public class Rating {

    private String userrPhone,
            foodname, comment,phonnumber;

    private String rating;

    public Rating() {
    }

    public Rating(String userrPhone, String foodname, String comment, String rating) {
        this.userrPhone = userrPhone;
        this.foodname = foodname;
        this.comment = comment;
        this.rating = rating;
    }

    public Rating(String userrPhone, String foodname, String comment, String phonnumber, String rating) {
        this.userrPhone = userrPhone;
        this.foodname = foodname;
        this.comment = comment;
        this.phonnumber = phonnumber;
        this.rating = rating;
    }

    public String getPhonnumber() {
        return phonnumber;
    }

    public void setPhonnumber(String phonnumber) {
        this.phonnumber = phonnumber;
    }

    public String getComment() {
        return comment;
    }

    public String getUserrPhone() {
        return userrPhone;
    }

    public void setUserrPhone(String userrPhone) {
        this.userrPhone = userrPhone;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}