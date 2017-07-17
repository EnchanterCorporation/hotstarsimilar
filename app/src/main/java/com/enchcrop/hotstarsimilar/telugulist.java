package com.enchcrop.hotstarsimilar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 14/7/17.
 */

public class telugulist implements Parcelable{

    String sno;
    String image;
    String  name;
    String url;

    protected telugulist(Parcel in) {
        sno = in.readString();
        image = in.readString();
        name = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<telugulist> CREATOR = new Creator<telugulist>() {
        @Override
        public telugulist createFromParcel(Parcel in) {
            return new telugulist(in);
        }

        @Override
        public telugulist[] newArray(int size) {
            return new telugulist[size];
        }
    };

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
