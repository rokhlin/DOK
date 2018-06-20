package com.selfapps.dok.view;

import android.view.View;
import android.widget.GridView;

import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;

import java.util.ArrayList;

public interface DetailsView extends MvpView {

    View getImageContainer();
    GridView getGalleryContainer();
    void showTextContent(String content);
    void showPlacesContent(ArrayList<Place> places);
    void showPersonsContent(ArrayList<Person> persons);
    void showName(String name);
    void showMap(Location location);
    void showAdditionalScreen(String id, DataType type);
    void updateListAdapter(ArrayList<Place> places);
    void updateFab(int img);
}
