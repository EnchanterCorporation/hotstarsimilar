package com.enchcrop.hotstarsimilar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 14/7/17.
 */

public class tamillist implements Parcelable {
    String sno;
    String image;
    String url;
    String names;
    String descrip;
    String time;

    protected tamillist(Parcel in) {
        sno = in.readString();
        image = in.readString();
        url = in.readString();
        names = in.readString();
        descrip = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(image);
        dest.writeString(url);
        dest.writeString(names);
        dest.writeString(descrip);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<tamillist> CREATOR = new Creator<tamillist>() {
        @Override
        public tamillist createFromParcel(Parcel in) {
            return new tamillist(in);
        }

        @Override
        public tamillist[] newArray(int size) {
            return new tamillist[size];
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
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
