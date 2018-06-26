package com.selfapps.dok.presenter;

import com.selfapps.dok.view.ISearchView;
import com.selfapps.dok.view.MvpView;

public interface ISearchPresenter<V extends ISearchView> extends IPresenter<ISearchView>  {
    void onTextChanged(String query);
    void onPlaceSelected(String id);
    void onPersonSelected(String id);
    void onRouteSelected(String id);
}
