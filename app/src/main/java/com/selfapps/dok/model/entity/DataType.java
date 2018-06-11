package com.selfapps.dok.model.entity;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public enum DataType {
//    ARTICLE,
//    BOOK,
//    DIRECTORY,
//    HISTORY,
    POI,
    PERSON,
    ROUTE;



    public String getFileName() {
        return super.toString().toLowerCase()+".json";
    }

    public Type getType(){
        if (super.equals(POI)) {
            return new TypeToken<Place>(){}.getType();
        }else if (super.equals(PERSON)) {
            return new TypeToken<Person>(){}.getType();
        } else if (super.equals(ROUTE)) {
            return new TypeToken<Route>(){}.getType();
        } else {
            throw new UnsupportedOperationException("DateType conversion error");
        }
    }
}
