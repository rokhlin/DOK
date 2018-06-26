package com.selfapps.dok.model.entity;



import java.util.List;

public interface Entity {

    String getId();
    String getName();
    String getContent();
    String getImagePath();
    List<Place> getPoiList();
    List<Person> getPersonList();
    List<String> getImageList();
    boolean equals(Object o);
    boolean contains(String string);
}
