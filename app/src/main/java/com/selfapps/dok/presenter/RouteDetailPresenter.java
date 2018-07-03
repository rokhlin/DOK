package com.selfapps.dok.presenter;

import android.content.Intent;

import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.model.IDetailsModel;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.model.entity.Route;

public class RouteDetailPresenter extends BaseDetailsPresenter<IDetailsModel<Route>> {

    public RouteDetailPresenter(IDetailsModel<Route> model, String id) {
        super(model, id);
    }


    @Override
    public void onAdditionalScreenSelected() {
        view.showAdditionalScreen(id, DataType.ROUTE);
    }

    @Override
    public void onMapSelected(Location location) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mapIntent.setData(location.getGeoUri(model.getContent().getName()));
        App.getContext().startActivity(mapIntent);
    }

    @Override
    public void onPlaceSelected() {
        //Don't use here
    }


    public String getName(){
        return model.getContent().getName();
    }

    @Override
    public void viewIsReady() {
        model.loadImage(view.getImageContainer());
        view.showName(model.getContent().getName());
        view.updateFab(R.drawable.additional_content_img);
        //view.showTextContent(model.getContent().getContent());//Removed because it was duplicated value
        view.showPlacesContent(model.getListPlaces());
    }
}