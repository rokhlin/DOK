package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class POIContent {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("mentionedByList")
    @Expose
    private List<Object> mentionedByList = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Object> getMentionedByList() {
        return mentionedByList;
    }

    public void setMentionedByList(List<Object> mentionedByList) {
        this.mentionedByList = mentionedByList;
    }
}
