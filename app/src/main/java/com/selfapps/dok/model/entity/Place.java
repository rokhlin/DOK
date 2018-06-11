package com.selfapps.dok.model.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place implements Entity {
////Example
//    {
//        "id":"630cc946-f47c-4ed6-8adb-a1591d8cfc8e",
//            "articleId":null,
//            "article":null,
//            "location":{
//        "latitude":48.4620939,
//                "longitude":35.05175059999999
//    },
//        "data":{
//        "ru":{
//            "name":"�������� ��� ����� ����������",
//                    "content":null,
//                    "address":"��. �����������, 2, ������, ���������������� �������, �������, 49000",
//                    "mentionedByList":[
//
//        ]
//        },
//        "en":{
//            "name":null,
//                    "content":null,
//                    "address":null,
//                    "mentionedByList":[
//
//        ]
//        }
//    },
//        "imageList":[
//        "b886ee61-fee7-4480-9e42-149a961b3dc2.jpg",
//                "b35d8632-69a9-4b7c-8ae8-ed63b5e4e912.jpg"
//    ],
//        "created":1527256731391,
//            "updated":1527256924791
//    }

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("articleId")
    @Expose
    private String articleId;

//    @SerializedName("article")
//    @Expose
//    private String article;

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

//    public String getArticle() {
//        return article;
//    }
//
//    public void setArticle(String article) {
//        this.article = article;
//    }

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
}