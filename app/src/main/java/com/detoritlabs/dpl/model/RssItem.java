package com.detoritlabs.dpl.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andrewgiang on 7/11/14.
 */
public class RssItem implements Parcelable{

    String title;
    String link;
    String description;
    String pubDate;

    public RssItem(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }
    RssItem(Parcel in) {
        title= in.readString();
        link = in.readString();
        description = in.readString();
        pubDate = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(description);
        dest.writeString(pubDate);
    }

    public static final Parcelable.Creator<RssItem> CREATOR
            = new Parcelable.Creator<RssItem>() {
        public RssItem createFromParcel(Parcel in) {
            return new RssItem(in);
        }

        public RssItem[] newArray(int size) {
            return new RssItem[size];
        }
    };
}
