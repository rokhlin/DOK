package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Route implements Entity {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("data")
    @Expose
    private RouteData data;
    @SerializedName("imageList")
    @Expose
    private List<String> imageList = null;
    @SerializedName("poiIdList")
    @Expose
    private List<String> poiIdList = null;
    @SerializedName("poiList")
    @Expose
    private List<Place> poiList = null;
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

    public RouteData getData() {
        return data;
    }

    public void setData(RouteData data) {
        this.data = data;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<String> getPoiIdList() {
        return poiIdList;
    }

    public void setPoiIdList(List<String> poiIdList) {
        this.poiIdList = poiIdList;
    }

    public List<Place> getPoiList() {
        return poiList;
    }

    public void setPoiList(List<Place> poiList) {
        this.poiList = poiList;
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
