package com.example.jboeser.favoriteseries;

/**
 * Created by j.boeser on 15-2-2017.
 */

public class ListItem {

    private String title;
    private String seizoen;

    public ListItem(String title, String seizoen) {
        this.title = title;
        this.seizoen = seizoen;
    }

    public String getTitle() {
        return title;
    }

    public String getSeizoen() {
        return seizoen;
    }
}
