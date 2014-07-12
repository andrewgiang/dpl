package com.detoritlabs.dpl.model;

import java.util.Date;

/**
 * Created by andrewgiang on 7/11/14.
 */
public class RssItem {

    String title;
    String link;
    String description;
    String pubDate;

    public RssItem(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
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
}
