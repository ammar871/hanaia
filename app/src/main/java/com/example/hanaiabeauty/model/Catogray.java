package com.example.hanaiabeauty.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.hanaiabeauty.roomdatabase.DateConverter;

@Entity(tableName = "detail")
@TypeConverters({DateConverter.class})
public class  Catogray implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int uid;


    protected Catogray(Parcel in) {
        uid = in.readInt();
        number = in.readString();
        name = in.readString();
        image = in.readString();
        size = in.readString();
        colore = in.readString();
        desc = in.readString();
        price = in.readString();
        date = in.readString();
    }

    public static final Creator<Catogray> CREATOR = new Creator<Catogray>() {
        @Override
        public Catogray createFromParcel(Parcel in) {
            return new Catogray(in);
        }

        @Override
        public Catogray[] newArray(int size) {
            return new Catogray[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ColumnInfo(name = "number")
    private String number;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "size")
    private String size;

    @ColumnInfo(name = "colore")
    private String colore;

    @ColumnInfo(name = "desc")
    private String desc;

    @ColumnInfo(name = "price")
    private String price;


    private String date;

    public @Ignore Catogray(String number, String name, String image, String size, String colore, String desc, String price, String date) {
        this.number = number;
        this.name = name;
        this.image = image;
        this.size = size;
        this.colore = colore;
        this.desc = desc;
        this.price = price;
        this.date = date;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public Catogray() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public @Ignore Catogray(String number, String name, String image, String desc, String price) {

        this.number = number;
        this.name = name;
        this.image = image;
        this.desc = desc;
        this.price = price;
    }



    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(number);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(size);
        dest.writeString(colore);
        dest.writeString(desc);
        dest.writeString(price);
        dest.writeString(date);
    }
}
