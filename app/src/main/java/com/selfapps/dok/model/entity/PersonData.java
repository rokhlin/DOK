package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonData {

    @SerializedName("ru")
    @Expose
    private PersonContent ru;
    @SerializedName("en")
    @Expose
    private PersonContent en;

    public PersonContent getRu() {
        return ru;
    }

    public void setRu(PersonContent ru) {
        this.ru = ru;
    }

    public PersonContent getEn() {
        return en;
    }

    public void setEn(PersonContent en) {
        this.en = en;
    }

}