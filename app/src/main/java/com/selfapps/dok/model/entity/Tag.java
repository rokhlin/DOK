package com.selfapps.dok.model.entity;

public class Tag {
   public String type;
   public String data;

    public Tag(String type, String data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
