package com.enchcrop.hotstarsimilar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 14/7/17.
 */

public class listbased implements Parcelable {
    String sno;
    String image;
    String names;
    String url;
    String lang;
    String descrip;
    String time;

    protected listbased(Parcel in) {
        sno = in.readString();
        image = in.readString();
        names = in.readString();
        url = in.readString();
        lang = in.readString();
        descrip = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(image);
        dest.writeString(names);
        dest.writeString(url);
        dest.writeString(lang);
        dest.writeString(descrip);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<listbased> CREATOR = new Creator<listbased>() {
        @Override
        public listbased createFromParcel(Parcel in) {
            return new listbased(in);
        }

        @Override
        public listbased[] newArray(int size) {
            return new listbased[size];
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

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
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
