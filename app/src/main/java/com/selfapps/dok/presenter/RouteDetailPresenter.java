package com.selfapps.dok.presenter;

import com.selfapps.dok.R;
import com.selfapps.dok.model.IDetailsModel;
import com.selfapps.dok.model.entity.DataType;

import com.selfapps.dok.model.entity.ExpListGroup;
import com.selfapps.dok.model.entity.Route;

import java.util.ArrayList;

public class RouteDetailPresenter extends BaseDetailsPresenter<IDetailsModel<Route>> {

    public RouteDetailPresenter(IDetailsModel<Route> model, String id) {
        super(model, id);
    }


    @Override
    public void onAdditionalScreenSelected() {
        view.showAdditionalScreen(id, DataType.ROUTE);
    }

    @Override
    public void onMapSelected() {
        //Don't use here
    }

    @Override
    public void onPlaceSelected() {
        //Don't use here
    }

    @Override
    public void viewIsReady() {
        model.loadImage(view.getImageContainer());
        view.showName(model.getContent().getName());
        view.updateFab(R.drawable.additional_content_img);

        view.showPlacesContent(model.getListPlaces());
    }
}