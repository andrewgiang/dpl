
package com.detoritlabs.dpl.api.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Cover implements Parcelable {

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.large);
        dest.writeString(this.medium);
    }

    public Cover() {
    }

    private Cover(Parcel in) {
        this.small = in.readString();
        this.large = in.readString();
        this.medium = in.readString();
    }

    public static final Parcelable.Creator<Cover> CREATOR = new Parcelable.Creator<Cover>() {
        public Cover createFromParcel(Parcel source) {
            return new Cover(source);
        }

        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };

    @Override
    public String toString() {
        return "Cover{" +
                "medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                ", small='" + small + '\'' +
                '}';
    }
}
