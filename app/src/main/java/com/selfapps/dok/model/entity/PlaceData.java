package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceData {

    @SerializedName("ru")
    @Expose
    private POIContent ru;
    @SerializedName("en")
    @Expose
    private POIContent en;

    public POIContent getRu() {
        return ru;
    }

    public void setRu(POIContent ru) {
        this.ru = ru;
    }

    public POIContent getEn() {
        return en;
    }

    public void setEn(POIContent en) {
        this.en = en;
    }

}