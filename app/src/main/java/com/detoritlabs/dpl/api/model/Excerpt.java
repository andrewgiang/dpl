
package com.detoritlabs.dpl.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
public class Excerpt implements Parcelable {

    private String comment;
    private String text;
    @SerializedName("first_sentence")
    private Boolean firstSentence;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getFirstSentence() {
        return firstSentence;
    }

    public void setFirstSentence(Boolean firstSentence) {
        this.firstSentence = firstSentence;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.comment);
        dest.writeString(this.text);
        dest.writeValue(this.firstSentence);
    }

    public Excerpt() {
    }

    private Excerpt(Parcel in) {
        this.comment = in.readString();
        this.text = in.readString();
        this.firstSentence = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Excerpt> CREATOR = new Parcelable.Creator<Excerpt>() {
        public Excerpt createFromParcel(Parcel source) {
            return new Excerpt(source);
        }

        public Excerpt[] newArray(int size) {
            return new Excerpt[size];
        }
    };

    @Override
    public String toString() {
        return "Excerpt{" +
                "comment='" + comment + '\'' +
                ", text='" + text + '\'' +
                ", firstSentence=" + firstSentence +
                '}';
    }
}
