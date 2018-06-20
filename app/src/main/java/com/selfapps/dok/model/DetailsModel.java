package com.selfapps.dok.model;

import android.view.View;
import android.widget.ImageView;

import com.selfapps.dok.model.entity.IModel;
import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;

import java.util.ArrayList;

public interface DetailsModel<C> extends IModel{

        C getContent();
        void loadImage(View container);
        Location getLocation();
        String getId();
        ArrayList<Place> getListPlaces();
        ArrayList<Person> getListPersons();
        ArrayList<Route> getListRoutes();
        //TODO Add Gallery Grid
}
