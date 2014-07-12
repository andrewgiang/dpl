package com.detoritlabs.dpl;

/**
 * Created by andrewgiang on 7/11/14.
 */
public enum Section {
    News(R.drawable.ic_news),
    Events(R.drawable.ic_event),
    Scanner(R.drawable.ic_scan),
    More(R.drawable.ic_more);

    private final int icon;

    Section(int icon) {
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle(){
        return this.name();
    }
}
