package com.enchcrop.hotstarsimilar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 14/7/17.
 */

public class languagelist implements Parcelable {
    String sno;
    String lang;
    String image;
    String num;

    protected languagelist(Parcel in) {
        sno = in.readString();
        lang = in.readString();
        image = in.readString();
        num = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(lang);
        dest.writeString(image);
        dest.writeString(num);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<languagelist> CREATOR = new Creator<languagelist>() {
        @Override
        public languagelist createFromParcel(Parcel in) {
            return new languagelist(in);
        }

        @Override
        public languagelist[] newArray(int size) {
            return new languagelist[size];
        }
    };

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}