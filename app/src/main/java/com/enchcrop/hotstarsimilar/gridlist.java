package com.enchcrop.hotstarsimilar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 13/7/17.
 */

public class gridlist implements Parcelable{
    String sno;
    String image;
    String names;
    String url;
    String descrip;
    String time;

    protected gridlist(Parcel in) {
        sno = in.readString();
        image = in.readString();
        names = in.readString();
        url = in.readString();
        descrip = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sno);
        dest.writeString(image);
        dest.writeString(names);
        dest.writeString(url);
        dest.writeString(descrip);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<gridlist> CREATOR = new Creator<gridlist>() {
        @Override
        public gridlist createFromParcel(Parcel in) {
            return new gridlist(in);
        }

        @Override
        public gridlist[] newArray(int size) {
            return new gridlist[size];
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
