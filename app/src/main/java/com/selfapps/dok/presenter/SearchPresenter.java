package com.selfapps.dok.presenter;

import com.selfapps.dok.model.ISearchModel;
import com.selfapps.dok.model.SearchModel;
import com.selfapps.dok.view.ISearchView;

public class SearchPresenter implements ISearchPresenter<ISearchView> {
    private ISearchModel model;
    private ISearchView view;

    public SearchPresenter(ISearchView view) {
        this.view = view;
        this.model = new SearchModel();
    }

    @Override
    public void onTextChanged(String query) {
        view.showResultList(model.getResultSet(query.toLowerCase()));
        view.expandGroupItems();
    }

    @Override
    public void onPlaceSelected(String id) {
        view.showPlace(id);
    }

    @Override
    public void onPersonSelected(String id) {
        view.showPerson(id);
    }

    @Override
    public void onRouteSelected(String id) {
        view.showRoute(id);
    }

    @Override
    public void attachView(ISearchView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void destroy() {
        detachView();
        model = null;
    }

    @Override
    public void viewIsReady() {
        view.expandGroupItems();
    }
}
