package com.selfapps.dok.presenter;

import android.content.Intent;
import android.net.Uri;

import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.model.IDetailsModel;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.model.entity.Place;

public class PlaceDetailPresenter extends BaseDetailsPresenter<IDetailsModel<Place>> {

    public PlaceDetailPresenter(IDetailsModel<Place> model, String id) {
        super(model, id);
    }


    @Override
    public void onAdditionalScreenSelected() {
        view.showAdditionalScreen(id, DataType.PLACE);
    }

    @Override
    public void onMapSelected() {
        Location location = model.getLocation();
        Uri gmmIntentUri = Uri.parse("geo:"+location.getLatitude()+","+location.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
       // mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(App.getContext().getPackageManager()) != null) {
            App.getContext().startActivity(mapIntent);
        }
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
        view.showTextContent(model.getContent().getContent());
        view.showMap(model.getLocation());
    }
}
