package com.selfapps.dok.presenter;

import com.selfapps.dok.LoadListContract;
import com.selfapps.dok.view.MvpView;


public class PlacesPresenter extends BasePresenter<MvpView> implements LoadListContract.Presenter {


    @Override
    public void onLoad() {

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void attachView(MvpView mvpView) {
        view = mvpView;
    }

    @Override
    public void viewIsReady() {

    }
}
