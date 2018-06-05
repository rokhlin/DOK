package com.selfapps.dok.presenter;

import com.selfapps.dok.view.MvpView;

public interface IPresenter<T extends MvpView> {

    void attachView(T view);
    void detachView();
    void destroy();
    void viewIsReady();
}
