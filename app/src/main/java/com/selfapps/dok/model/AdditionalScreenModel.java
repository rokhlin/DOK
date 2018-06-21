package com.selfapps.dok.model;

import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Entity;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.utils.Converter;
import com.selfapps.dok.utils.PreferencesUtil;

import java.util.ArrayList;

public class AdditionalScreenModel implements IAdditionalScreenModel {
    Entity entity;

    public AdditionalScreenModel(String id, String strType) {
        DataType type = DataType.getType(strType);
        entity = getEntityById(id,type);
    }

    private Entity getEntityById(String id, DataType type) {
        ArrayList<Entity> entities = Converter.getEntityFromString(type, PreferencesUtil.getData(type));
        switch (type){
            case PLACE:
                return entities.get(entities.indexOf(new Place(id)));
            case PERSON:
                return entities.get(entities.indexOf(new Person(id)));
            case ROUTE:
                return entities.get(entities.indexOf(new Route(id)));
            default:
                return null;
        }
    }

    @Override
    public ArrayList<Place> getListPlaces() {
        return (ArrayList<Place>) entity.getPoiList();
    }

    @Override
    public ArrayList<Person> getListPersons() {
        return (ArrayList<Person>) entity.getPersonList();
    }

    @Override
    public ArrayList<String> getListImages() {
        return (ArrayList<String>) entity.getImageList();
    }


}
