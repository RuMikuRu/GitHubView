package com.example.myapplication.NewAppPlease.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @SerializedName("full_name")
    @Expose
    private String name;
    @SerializedName("owner")
    @Expose
    private Owner avatarUrl;
    @SerializedName("html_url")
    @Expose
    private String html_url;
    @SerializedName("description")
    @Expose
    private String description;

    public Item(String name,Owner avatarUrl,String html_url, String description)
    {
        this.name = name;
        this.avatarUrl=avatarUrl;
        this.html_url=html_url;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription(){return description;}

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getAvatarUrl()
    {
        return avatarUrl;
    }

    public void setAvatarUrl(Owner avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
    public String getHtml_url()
    {
        return html_url;
    }
}
