package com.selfapps.dok.view;

import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;

import java.util.ArrayList;

public interface IAdditionalScreenView extends MvpView {
    String getId();
    String getType();
    void showImages(ArrayList<String> imageList);
    void showPersons(ArrayList<Person> persons);
    void showPlaces(ArrayList<Place> places);

    void showImage(String path);
    void showPerson(String id);
    void showPlace(String id);
}
