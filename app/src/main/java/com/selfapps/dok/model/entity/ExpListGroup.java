package com.selfapps.dok.model.entity;

import java.util.ArrayList;

public class ExpListGroup {

    private DataType type;

    private ArrayList<Person> persons;
    private ArrayList<Route> routes;
    private ArrayList<Place> places;
    private ArrayList<String> imageList;

    public DataType getType() {
        return type;
    }


    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.type =DataType.PERSON;
        this.persons = persons;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.type =DataType.ROUTE;
        this.routes = routes;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.type =DataType.PLACE;
        this.places = places;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.type =DataType.IMAGE;
        this.imageList = imageList;
    }

    public int size(){
        try {
            switch (type){
                case ROUTE:
                    return routes.size();
                case PERSON:
                    return persons.size();
                case PLACE:
                    return places.size();
                case IMAGE:
                    return imageList.size();
            }
        } catch (NullPointerException e) { }
        return 0;
    }

//    public boolean contains(String query){
//        try {
//            switch (type){
//                case ROUTE:
//                    return routes.contains(query);
//                case PERSON:
//                    return persons.contains(query);
//                case PLACE:
//                    return places.contains(query);
//                case IMAGE:
//                    return false;
//            }
//        } catch (NullPointerException e) { }
//        return false;
//    }

}
