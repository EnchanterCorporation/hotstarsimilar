package com.enchcrop.hotstarsimilar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter19 on 14/7/17.
 */

public class hindilidt implements Parcelable {
    String name;
    String sno;
    String image;
    String url;

    protected hindilidt(Parcel in) {
        name = in.readString();
        sno = in.readString();
        image = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(sno);
        dest.writeString(image);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<hindilidt> CREATOR = new Creator<hindilidt>() {
        @Override
        public hindilidt createFromParcel(Parcel in) {
            return new hindilidt(in);
        }

        @Override
        public hindilidt[] newArray(int size) {
            return new hindilidt[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
