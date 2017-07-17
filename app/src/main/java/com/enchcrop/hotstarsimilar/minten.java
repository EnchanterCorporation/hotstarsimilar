package com.enchcrop.hotstarsimilar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 15/7/17.
 */

public class minten implements Parcelable{
    String sno;
    String image1;
    String names;
    String url1;
    String time;
    String descr;

    protected minten(Parcel in) {
        sno = in.readString();
        image1 = in.readString();
        names = in.readString();
        url1 = in.readString();
        time = in.readString();
        descr = in.readString();
    }

    public static final Creator<minten> CREATOR = new Creator<minten>() {
        @Override
        public minten createFromParcel(Parcel in) {
            return new minten(in);
        }

        @Override
        public minten[] newArray(int size) {
            return new minten[size];
        }
    };

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sno);
        parcel.writeString(image1);
        parcel.writeString(names);
        parcel.writeString(url1);
        parcel.writeString(time);
        parcel.writeString(descr);
    }
}

