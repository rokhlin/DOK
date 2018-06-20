package com.selfapps.dok.presenter;

import com.selfapps.dok.view.DetailsView;

public interface IDetailsPresenter<S extends DetailsView> extends IPresenter<DetailsView> {

    void onAdditionalScreenSelected();
    void onMapSelected();
    void onPlaceSelected();
}
