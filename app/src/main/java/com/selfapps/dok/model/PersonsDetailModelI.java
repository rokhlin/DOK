package com.selfapps.dok.model;

import android.view.View;
import android.widget.ImageView;

import com.selfapps.dok.R;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Entity;
import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.utils.Converter;
import com.selfapps.dok.utils.PreferencesUtil;


import java.util.ArrayList;

public class PersonsDetailModelI implements IDetailsModel<Person> {
    Person content;

    public PersonsDetailModelI(String id, DataType type) {
        //TODO Extract to ABSTRACT after
        ArrayList<Entity> entities = Converter.getEntityFromString(type,PreferencesUtil.getData(type));
        content = (Person) entities.get(entities.indexOf(new Person(id)));
    }

    @Override
    public Person getContent() {
        return content;
    }

    @Override
    public void loadImage(View container) {
        Communicator.loadImageFilterSepia((ImageView) container,content.getImagePath(), R.drawable.place_holder_vertical);
    }


    @Override
    public Location getLocation() {
        return null;//Don't use here
    }

    @Override
    public String getId() {
        return content.getId();
    }

    @Override
    public ArrayList<Place> getListPlaces() {
        return null;//Don't use here
    }


    @Override
    public ArrayList<Person> getListPersons() {
        return null;//Don't use here
    }

    @Override
    public ArrayList<Route> getListRoutes() {
        return null;//Don't use here
    }

}
