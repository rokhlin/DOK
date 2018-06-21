package com.selfapps.dok.presenter;

import com.selfapps.dok.model.IDetailsModel;
import com.selfapps.dok.view.DetailsView;

public abstract class BaseDetailsPresenter<M extends IDetailsModel> implements IDetailsPresenter<DetailsView> {
    String id;
    DetailsView view;
    M model;

    BaseDetailsPresenter(M model, String id) {
        this.id = id;
        this.model = model;
    }

    @Override
    public void attachView(DetailsView view) {
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
}
