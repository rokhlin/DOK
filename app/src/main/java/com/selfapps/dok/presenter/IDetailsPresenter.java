package com.selfapps.dok.presenter;

import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.view.DetailsView;

public interface IDetailsPresenter<S extends DetailsView> extends IPresenter<DetailsView> {

    void onAdditionalScreenSelected();
    void onMapSelected(Location location);
    void onPlaceSelected();
}
