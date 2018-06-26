package com.selfapps.dok.view;

import com.selfapps.dok.model.entity.Entity;
import com.selfapps.dok.model.entity.ExpListGroup;

import java.util.ArrayList;

public interface ISearchView extends MvpView {
    void showResultList(ArrayList<ExpListGroup> resultSet);
    void showPerson(String id);
    void showPlace(String id);
    void showRoute(String id);
    void setSearchViewActive();
}
