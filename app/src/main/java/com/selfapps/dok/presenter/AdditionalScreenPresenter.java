package com.selfapps.dok.presenter;

import com.selfapps.dok.model.AdditionalScreenModel;
import com.selfapps.dok.model.IAdditionalScreenModel;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.view.IAdditionalScreenView;

import java.util.ArrayList;

public class AdditionalScreenPresenter implements IAdditionScreenPresenter<IAdditionalScreenView> {
    private IAdditionalScreenView view;
    private IAdditionalScreenModel model;

    public AdditionalScreenPresenter(IAdditionalScreenView view) {
        this.view = view;
        model = new AdditionalScreenModel(view.getId(),view.getType());
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onImageSelected(String path) {
        view.showImage(path);
    }

    @Override
    public void onPlaceSelected(String id) {
        view.showPlace(id);
    }

    @Override
    public void onPersonSelected(String id) {
        view.showPerson(id);
    }

    @Override
    public void attachView(IAdditionalScreenView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void destroy() {
        detachView();
    }

    @Override
    public void viewIsReady() {
        ArrayList<String> images = model.getListImages();
        ArrayList<Person> persons = model.getListPersons();
        ArrayList<Place> places = model.getListPlaces();

        if(images != null && !images.isEmpty())
            view.showImages(images);

        if(persons != null && !persons.isEmpty())
            view.showPersons(persons);

        if(places != null && !places.isEmpty())
            view.showPlaces(places);
    }
}
