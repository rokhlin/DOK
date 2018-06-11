package com.selfapps.dok.presenter;

import com.selfapps.dok.view.SplashView;

public interface ISplashPresenter<S extends SplashView> extends IPresenter<SplashView> {

    boolean isValidVersion();
    //

}
