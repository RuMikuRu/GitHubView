package com.example.myapplication.NewApp;

import com.google.gson.annotations.SerializedName;

public class RepoModel {
    @SerializedName ("full-name") String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
