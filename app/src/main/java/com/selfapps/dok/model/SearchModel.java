package com.selfapps.dok.model;

import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.ExpListGroup;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.utils.Converter;
import com.selfapps.dok.utils.PreferencesUtil;
import com.selfapps.dok.view.ISearchView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SearchModel implements ISearchModel {
    private HashSet<Person> persons;
    private HashSet<Place> places;
    private HashSet<Route> routes;


    public SearchModel() {
        persons = new HashSet<>(Converter.getPersonsFromString(PreferencesUtil.getData(DataType.PERSON)));
        places = new HashSet<>(Converter.getPlacesFromString(PreferencesUtil.getData(DataType.PLACE)));
        routes = new HashSet<>(Converter.getRoutesFromString(PreferencesUtil.getData(DataType.ROUTE)));
    }

    @Override
    public ArrayList<ExpListGroup> getResultSet(String query) {
        ArrayList<ExpListGroup> resultSet = new ArrayList<>();

        ExpListGroup personsGroup = getPersonsGroup(query);
        if(personsGroup!=null)
            resultSet.add(personsGroup);

        ExpListGroup placesGroup = getPlacesGroup(query);
        if(placesGroup!=null)
        resultSet.add(placesGroup);

        ExpListGroup routesGroup = getRoutesGroup(query);
        if(routesGroup!=null)
        resultSet.add(routesGroup);
        return resultSet;
    }

    private ExpListGroup getPersonsGroup(String query) {
        ExpListGroup group = new ExpListGroup();
        ArrayList<Person> res = new ArrayList<>();
        for (Person item : persons){
            if(item.contains(query))
                res.add(item);
        }
        if(!res.isEmpty()){
            group.setPersons(res);
            return group;
        }
        return null;
    }

    private ExpListGroup getPlacesGroup(String query) {
        ExpListGroup group = new ExpListGroup();
        ArrayList<Place> res = new ArrayList<>();
        for (Place item : places){
            if(item.contains(query))
                res.add(item);
        }
        if(!res.isEmpty()){
            group.setPlaces(res);
            return group;
        }
         return null;
    }

    private ExpListGroup getRoutesGroup(String query) {
        ExpListGroup group = new ExpListGroup();
        ArrayList<Route> res = new ArrayList<>();
        for (Route item : routes){
            if(item.contains(query))
                res.add(item);
        }
        if(!res.isEmpty()){
            group.setRoutes(res);
            return group;
        }
        return null;
    }
}
