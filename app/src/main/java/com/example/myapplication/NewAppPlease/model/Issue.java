package com.example.myapplication.NewAppPlease.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Issue {
    @SerializedName("title")
    @Expose
    private String title;

    public String getTitle() {
        return title;
    }
}
