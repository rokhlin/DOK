package com.selfapps.dok.model.entity;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.utils.Utils;

import java.util.List;
import java.util.Objects;

public class Route implements Entity {
    private static final String TAG = "Route";
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
    private long created;
    @SerializedName("updated")
    @Expose
    private long updated;


    public Route(String id) {
        this.id = id;
    }

    public Route() { }

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

    @Override
    public List<Person> getPersonList() {
        return null;
    }

    public void setPoiList(List<Place> poiList) {
        this.poiList = poiList;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    @Override
    public String getName() {
        try {
            switch (Utils.getCurrentLanguage()){
                case En:
                    return data.getEn().getName();
                case Ru:
                    return data.getRu().getName();
                default:
                    return null;
            }
        } catch (NullPointerException e) {
            Log.d(TAG, "getName: is null");
        }
        return App.getContext().getString(R.string.name_undefined);
    }

    @Override
    public String getContent() {
        try {
            switch (Utils.getCurrentLanguage()){
                case En:
                    return data.getEn().getContent();
                case Ru:
                    return data.getRu().getContent();
                default:
                    return null;
            }
        } catch (NullPointerException e) {
            Log.d(TAG, "getContent: is null");
        }
        return "";
    }

    @Override
    public String getImagePath() {
        try{
            if (getImageList()!=null && getImageList().size() != 0 )
                return getImageList().get(0);
        } catch (IndexOutOfBoundsException e) {
            Log.d("APP","Logo is empty. Image loading error "+e.getMessage());
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", data=" + data +
                ", imageList=" + imageList +
                ", poiIdList=" + poiIdList +
                ", poiList=" + poiList +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
