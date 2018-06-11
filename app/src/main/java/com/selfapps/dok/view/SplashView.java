package com.selfapps.dok.view;

public interface SplashView extends MvpView {

    void updateProgress(boolean isActive);
    void postProgress(int progress);
    void setText(String text);
    void startMainActivity();
}
