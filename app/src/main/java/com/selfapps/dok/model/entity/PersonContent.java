package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonContent {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("content")
    @Expose
    private Object content;
    @SerializedName("mentionedByList")
    @Expose
    private List<Object> mentionedByList = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public List<Object> getMentionedByList() {
        return mentionedByList;
    }

    public void setMentionedByList(List<Object> mentionedByList) {
        this.mentionedByList = mentionedByList;
    }

}
