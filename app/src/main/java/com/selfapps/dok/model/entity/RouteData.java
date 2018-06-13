package com.selfapps.dok.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RouteData {

    @SerializedName("ru")
    @Expose
    private RouteContent ru;
    @SerializedName("en")
    @Expose
    private RouteContent en;

    public RouteContent getRu() {
        return ru;
    }

    public void setRu(RouteContent ru) {
        this.ru = ru;
    }

    public RouteContent getEn() {
        return en;
    }

    public void setEn(RouteContent en) {
        this.en = en;
    }

}
