package com.selfapps.dok;

import com.selfapps.dok.model.entity.Entity;
import com.selfapps.dok.presenter.IPresenter;
import com.selfapps.dok.view.MvpView;

import java.util.List;

public interface LoadListContract {
    interface View extends MvpView {
        void showList(List<Entity> entities);
    }

    interface Presenter extends IPresenter<MvpView> {
        void onLoad();
        void onUpdate();
    }
}
