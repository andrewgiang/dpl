
package com.detoritlabs.dpl.api.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Publisher implements Parcelable {

    private String name;

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

    public Publisher() {
    }

    private Publisher(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Publisher> CREATOR = new Parcelable.Creator<Publisher>() {
        public Publisher createFromParcel(Parcel source) {
            return new Publisher(source);
        }

        public Publisher[] newArray(int size) {
            return new Publisher[size];
        }
    };

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                '}';
    }
}
