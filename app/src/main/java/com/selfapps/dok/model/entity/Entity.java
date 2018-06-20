package com.selfapps.dok.model.entity;

public interface Entity {

    String getId();
    String getName();
    String getContent();
    String getImagePath();
    boolean equals(Object o);
}
