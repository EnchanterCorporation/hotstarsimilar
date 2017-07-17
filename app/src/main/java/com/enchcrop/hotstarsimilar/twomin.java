package com.enchcrop.hotstarsimilar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 15/7/17.
 */

public class twomin implements Parcelable{
    String sno;
    String url1;
    String image1;
    String names;
    String time;
    String descr;

    protected twomin(Parcel in) {
        sno = in.readString();
        url1 = in.readString();
        image1 = in.readString();
        names = in.readString();
        time = in.readString();
        descr = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(url1);
        dest.writeString(image1);
        dest.writeString(names);
        dest.writeString(time);
        dest.writeString(descr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<twomin> CREATOR = new Creator<twomin>() {
        @Override
        public twomin createFromParcel(Parcel in) {
            return new twomin(in);
        }

        @Override
        public twomin[] newArray(int size) {
            return new twomin[size];
        }
    };

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
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
}
