package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonData {

    @SerializedName("ru")
    @Expose
    private MainContent ru;
    @SerializedName("en")
    @Expose
    private MainContent en;

    public MainContent getRu() {
        return ru;
    }

    public void setRu(MainContent ru) {
        this.ru = ru;
    }

    public MainContent getEn() {
        return en;
    }

    public void setEn(MainContent en) {
        this.en = en;
    }

}