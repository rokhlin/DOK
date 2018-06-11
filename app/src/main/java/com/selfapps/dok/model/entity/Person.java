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
    private String articleId;
//    @SerializedName("article")
//    @Expose
//    private Object article;
    @SerializedName("data")
    @Expose
    private PersonData personData;
    @SerializedName("imageList")
    @Expose
    private List<String> imageList = null;
    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("updated")
    @Expose
    private long updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

//    public Object getArticle() {
//        return article;
//    }
//
//    public void setArticle(Object article) {
//        this.article = article;
//    }

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

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", articleId=" + articleId +
               // ", article=" + article +
                ", personData=" + personData +
                ", imageList=" + imageList +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}