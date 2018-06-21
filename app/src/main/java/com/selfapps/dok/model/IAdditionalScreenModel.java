package com.selfapps.dok.model;

import com.selfapps.dok.model.entity.IModel;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;

import java.util.ArrayList;

public interface IAdditionalScreenModel extends IModel {
    ArrayList<Place> getListPlaces();
    ArrayList<Person> getListPersons();
    ArrayList<String> getListImages();
}
