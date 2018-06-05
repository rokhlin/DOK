package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Person implements Entity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("articleId")
    @Expose
    private Object articleId;
    @SerializedName("article")
    @Expose
    private Object article;
    @SerializedName("personData")
    @Expose
    private PersonData personData;
    @SerializedName("imageList")
    @Expose
    private List<String> imageList = null;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("updated")
    @Expose
    private Integer updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getArticleId() {
        return articleId;
    }

    public void setArticleId(Object articleId) {
        this.articleId = articleId;
    }

    public Object getArticle() {
        return article;
    }

    public void setArticle(Object article) {
        this.article = article;
    }

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

}