package com.selfapps.dok.model.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place implements Entity {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("articleId")
    @Expose
    private Object articleId;

    @SerializedName("article")
    @Expose
    private Object article;

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("personData")
    @Expose
    private PlaceData placeData;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PlaceData getPlaceData() {
        return placeData;
    }

    public void setPlaceData(PlaceData placeData) {
        this.placeData = placeData;
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