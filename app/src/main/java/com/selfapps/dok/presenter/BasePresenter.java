package com.selfapps.dok.presenter;

import com.selfapps.dok.view.MvpView;

public abstract class BasePresenter<V extends MvpView> implements IPresenter<V> {
    V view;


    @Override
    public void detachView() {
        view = null;
    }

    public V getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {

    }


}
