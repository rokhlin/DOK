package com.selfapps.dok.model.entity;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selfapps.dok.utils.Utils;

import java.util.List;
import java.util.Objects;

public class Person implements Entity {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("articleId")
    @Expose
    private String articleId;
    @SerializedName("article")
    @Expose
    private Article article;
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


    public Person(String id) {
        this.id = id;
    }

    public Person() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        switch (Utils.getCurrentLanguage()){
            case En:
                return personData.getEn().getName();
            case Ru:
                return personData.getRu().getName();
            default:
                return null;
        }
    }

    @Override
    public String getContent() {
        switch (Utils.getCurrentLanguage()){
            case En:
                return article.getData().getEn().getContent();
            case Ru:
                return article.getData().getRu().getContent();
            default:
                return null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}