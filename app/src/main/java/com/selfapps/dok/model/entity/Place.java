package com.selfapps.dok.model.entity;

import android.util.Log;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.utils.Utils;

public class Place implements Entity {
    private static final String TAG = "Place";

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("articleId")
    @Expose
    private String articleId;

    @SerializedName("article")
    @Expose
    private Article article;

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("data")
    @Expose
    private PlaceData placeData;

    @SerializedName("imageList")
    @Expose
    private List<String> imageList = null;

    @SerializedName("created")
    @Expose
    private long created;

    @SerializedName("updated")
    @Expose
    private long updated;

    public Place(String id) {
        this.id = id;
    }

    public Place() { }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {//TODO add NPE catching
        try {
            switch (Utils.getCurrentLanguage()){
                case En:
                    return placeData.getEn().getName();
                case Ru:
                    return placeData.getRu().getName();
                default:
                    return null;
            }
        } catch (NullPointerException e) {
            Log.d(TAG, "getName: is null");
        }
        return App.getContext().getString(R.string.name_undefined);
    }

    @Override
    public String getContent() {//TODO add NPE catching
        try {
            switch (Utils.getCurrentLanguage()){
                case En:
                    return article.getData().getEn().getContent();
                case Ru:
                    return article.getData().getRu().getContent();
                default:
                    return null;
            }
        } catch (NullPointerException e) {
            Log.d(TAG, "getContent: is null");
        }
        return "";
    }

    public String getAddress() {
        switch (Utils.getCurrentLanguage()){
            case En:
                return placeData.getEn().getAddress();
            case Ru:
                return placeData.getRu().getAddress();
            default:
                return "";
        }
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
    public List<Place> getPoiList() {
        return null;
    }

    @Override
    public List<Person> getPersonList() {
        return null;
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
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
        return "Place{" +
                "id='" + id + '\'' +
                ", articleId=" + articleId +
               // ", article='" + article + '\'' +
                ", location=" + location +
                ", placeData=" + placeData +
                ", imageList=" + imageList +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return Objects.equals(id, place.id);
    }

    @Override
    public boolean contains(String string) {
        return getName().toLowerCase().contains(string) |
                getAddress().toLowerCase().contains(string);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}