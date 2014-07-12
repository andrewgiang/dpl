package com.detoritlabs.dpl.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andrewgiang on 7/12/14.
 */
public class Subject implements Parcelable {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public Subject() {
    }

    private Subject(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Subject> CREATOR = new Parcelable.Creator<Subject>() {
        public Subject createFromParcel(Parcel source) {
            return new Subject(source);
        }

        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }
}
