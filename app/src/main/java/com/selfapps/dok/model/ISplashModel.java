package com.selfapps.dok.model;

import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Entity;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;

import java.util.ArrayList;

public interface ISplashModel {

    boolean isDataValid(DataType type);
    void requestPlaces(EntityProvider<Place> provider);
    void requestPersons(EntityProvider<Person> provider);
    void requestRoutes(EntityProvider<Route> provider);

}
