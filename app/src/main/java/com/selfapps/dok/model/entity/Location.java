package com.selfapps.dok.model.entity;

import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("latitude")
    @Expose
    private Double latitude;

    @SerializedName("longitude")
    @Expose
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Uri getGeoUri(String label){
        return Uri.parse("geo:"+getLatitude()+","+getLongitude()+"?z=16&q="+getLatitude()+","+getLongitude()+"("+label+")");
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}