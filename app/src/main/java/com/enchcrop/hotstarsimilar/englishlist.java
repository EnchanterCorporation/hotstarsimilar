package com.enchcrop.hotstarsimilar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 14/7/17.
 */

public class englishlist implements Parcelable {
    String sno;
    String names;
    String image;
    String url;
    String lang;
    String descrip;
    String time;

    protected englishlist(Parcel in) {
        sno = in.readString();
        names = in.readString();
        image = in.readString();
        url = in.readString();
        lang = in.readString();
        descrip = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(names);
        dest.writeString(image);
        dest.writeString(url);
        dest.writeString(lang);
        dest.writeString(descrip);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<englishlist> CREATOR = new Creator<englishlist>() {
        @Override
        public englishlist createFromParcel(Parcel in) {
            return new englishlist(in);
        }

        @Override
        public englishlist[] newArray(int size) {
            return new englishlist[size];
        }
    };

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
