package com.detoritlabs.dpl;

/**
 * Created by andrewgiang on 7/11/14.
 */
public enum Section {
    NEWS(R.drawable.ic_news),
    EVENTS(R.drawable.ic_event),
    CATALOG(R.drawable.ic_catalog),
    MORE(R.drawable.ic_more);

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
