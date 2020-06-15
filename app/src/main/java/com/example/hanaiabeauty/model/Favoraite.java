package com.example.hanaiabeauty.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;

public class Favoraite implements Parcelable {
    private int number;

    private String userphone,favname,size,colore,favprice,favdesc,facimage;

    public Favoraite(int number, String userphone, String favname, String favprice, String favdesc, String facimage) {
        this.number = number;
        this.userphone = userphone;
        this.favname = favname;
        this.favprice = favprice;
        this.favdesc = favdesc;
        this.facimage = facimage;
    }

    public Favoraite(int number, String favname, String favprice, String favdesc, String facimage) {
        this.number = number;
        this.favname = favname;
        this.favprice = favprice;
        this.favdesc = favdesc;
        this.facimage = facimage;
    }

    public Favoraite(String userphone, String favname,String size,String colore, String favprice, String favdesc, String facimage) {
        this.userphone = userphone;
        this.favname = favname;
        this.size=size;
        this.colore=colore;
        this.favprice = favprice;
        this.favdesc = favdesc;
        this.facimage = facimage;
    }

    public Favoraite() {
    }


    protected Favoraite(Parcel in) {
        number = in.readInt();
        userphone = in.readString();
        favname = in.readString();
        size = in.readString();
        colore = in.readString();
        favprice = in.readString();
        favdesc = in.readString();
        facimage = in.readString();
    }

    public static final Creator<Favoraite> CREATOR = new Creator<Favoraite>() {
        @Override
        public Favoraite createFromParcel(Parcel in) {
            return new Favoraite(in);
        }

        @Override
        public Favoraite[] newArray(int size) {
            return new Favoraite[size];
        }
    };

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }


    public String getFavname() {
        return favname;
    }

    public void setFavname(String favname) {
        this.favname = favname;
    }

    public String getFavprice() {
        return favprice;
    }

    public void setFavprice(String favprice) {
        this.favprice = favprice;
    }

    public String getFavdesc() {
        return favdesc;
    }

    public void setFavdesc(String favdesc) {
        this.favdesc = favdesc;
    }

    public String getFacimage() {
        return facimage;
    }

    public void setFacimage(String facimage) {
        this.facimage = facimage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(userphone);
        dest.writeString(favname);
        dest.writeString(size);
        dest.writeString(colore);
        dest.writeString(favprice);
        dest.writeString(favdesc);
        dest.writeString(facimage);
    }
}
