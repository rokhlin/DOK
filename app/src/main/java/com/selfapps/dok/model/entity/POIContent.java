package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class POIContent {
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("content")
    @Expose
    private Object content;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("mentionedByList")
    @Expose
    private List<Object> mentionedByList = null;

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public List<Object> getMentionedByList() {
        return mentionedByList;
    }

    public void setMentionedByList(List<Object> mentionedByList) {
        this.mentionedByList = mentionedByList;
    }
}
