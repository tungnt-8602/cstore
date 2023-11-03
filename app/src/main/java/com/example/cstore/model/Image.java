package com.example.cstore.model;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("_id")
    private String id;
    private String url;
    private String caption;

    public Image() {
    }

    public Image(String id, String url, String caption) {
        this.id = id;
        this.url = url;
        this.caption = caption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
