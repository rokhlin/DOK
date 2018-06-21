package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleData {

    @SerializedName("ru")
    @Expose
    private ArticleContent ru;
    @SerializedName("en")
    @Expose
    private ArticleContent en;

    public ArticleContent getRu() {
        return ru;
    }

    public void setRu(ArticleContent ru) {
        this.ru = ru;
    }

    public ArticleContent getEn() {
        return en;
    }

    public void setEn(ArticleContent en) {
        this.en = en;
    }

}