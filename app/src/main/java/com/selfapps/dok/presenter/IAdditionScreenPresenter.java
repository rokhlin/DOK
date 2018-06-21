package com.selfapps.dok.presenter;

import com.selfapps.dok.view.IAdditionalScreenView;

public interface IAdditionScreenPresenter<V extends IAdditionalScreenView>
                                                extends IPresenter<IAdditionalScreenView> {
    void onBackPressed();
    void onImageSelected(String path);
    void onPlaceSelected(String id);
    void onPersonSelected(String id);
}
