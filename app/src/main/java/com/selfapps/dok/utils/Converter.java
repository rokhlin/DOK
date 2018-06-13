package com.selfapps.dok.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converter {
    private static Gson gson = null;
    private static Converter converter = null;

    private Converter() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    private static Converter getInstance(){
        if(converter == null) converter = new Converter();
        return converter;
    }


    public static ArrayList<Object> getEntityFromString(DataType type, String data) throws UnsupportedOperationException {
        getInstance();
        //Converter example: Type collectionType = new TypeToken<ArrayList<Person>>(){}.getType();
        switch (type){
            case ROUTE:
                return gson.fromJson(data, new TypeToken<ArrayList<Route>>(){}.getType());
            case PERSON:
                return gson.fromJson(data, new TypeToken<ArrayList<Person>>(){}.getType());
            case POI:
                return gson.fromJson(data, new TypeToken<ArrayList<Place>>(){}.getType());
            default:
                return null;
        }
    }

    public static String entityToString(Object entity) throws UnsupportedOperationException {
        getInstance();
        return gson.toJson(entity);
    }



    public static ArrayList<Person> getPersonsFromString(String data) throws UnsupportedOperationException {
        Type collectionType = new TypeToken<ArrayList<Person>>(){}.getType();
        return gson.fromJson(data, collectionType);
    }

    public static ArrayList<Place> getPlacesFromString(String data) throws UnsupportedOperationException {
        Type collectionType = new TypeToken<ArrayList<Place>>(){}.getType();
        return gson.fromJson(data, collectionType);
    }

    public static ArrayList<Route> getRoutesFromString(String data) throws UnsupportedOperationException {
        Type collectionType = new TypeToken<ArrayList<Route>>(){}.getType();
        return gson.fromJson(data, collectionType);
    }


}
