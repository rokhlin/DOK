package com.selfapps.dok.model.entity;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.UnknownFormatFlagsException;

public enum DataType {
//    ARTICLE,
//    BOOK,
//    DIRECTORY,
//    HISTORY,
    PLACE,
    PERSON,
    ROUTE,
    IMAGE;//Only for Internal Use


    public String getFileName() {
        return super.toString().toLowerCase()+".json";
    }

    public Type getType(){
        if (super.equals(PLACE)) {
            return new TypeToken<Place>(){}.getType();
        }else if (super.equals(PERSON)) {
            return new TypeToken<Person>(){}.getType();
        } else if (super.equals(ROUTE)) {
            return new TypeToken<Route>(){}.getType();
        } else {
            throw new UnsupportedOperationException("DateType conversion error");
        }
    }

    public static DataType getType(String type){
        if(type.toLowerCase()
                .equals(DataType.PERSON.name().toLowerCase()))
            return PERSON;
        if(type.toLowerCase()
                .equals(DataType.PLACE.name().toLowerCase()))
            return PLACE;
        if(type.toLowerCase()
                .equals(DataType.ROUTE.name().toLowerCase()))
            return ROUTE;
        else throw new UnknownFormatFlagsException("DataType is unknown");
    }

}
