package com.selfapps.dok.presenter;

import android.content.Intent;

import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.model.IDetailsModel;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.model.entity.Place;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class PlaceDetailPresenter extends BaseDetailsPresenter<IDetailsModel<Place>> {
    private static final String TAG =" PlaceDetailPresenter" ;

    public PlaceDetailPresenter(IDetailsModel<Place> model, String id) {
        super(model, id);
    }


    @Override
    public void onAdditionalScreenSelected() {
        view.showAdditionalScreen(id, DataType.PLACE);
    }

    @Override
    public void onMapSelected(Location location) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        mapIntent.setData(location.getGeoUri(model.getContent().getName()));
        App.getContext().startActivity(mapIntent);
    }

    @Override
    public void onPlaceSelected() {
        //Don't use here
    }

    @Override
    public void viewIsReady() {
        model.loadImage(view.getImageContainer());
        view.showName(model.getContent().getName());
        view.updateFab(R.drawable.icons_about2);
        view.showTextContent(model.getContent().getContent());
        view.showMap(model.getLocation());
    }
}
