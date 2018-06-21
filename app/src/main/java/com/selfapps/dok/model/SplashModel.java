package com.selfapps.dok.model;

import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.utils.Constants;

import static com.selfapps.dok.utils.PreferencesUtil.getData;
import static com.selfapps.dok.utils.PreferencesUtil.getLong;
import static com.selfapps.dok.utils.PreferencesUtil.setLong;

public class SplashModel implements ISplashModel {

    public SplashModel() {

    }

    @Override
    public boolean isDataValid(DataType type) {
        return false;//TODO remove before release
//        return getData(type).length() > 4
//                & getLong(Constants.PREF_LAST_UPDATE,0) > getExpiredTime();
    }

    private long getExpiredTime() {
        return System.currentTimeMillis() - Constants.UPDATE_PERIOD_MILLS ;
    }

    private void setDataUpdatedTime() {
        setLong(Constants.PREF_LAST_UPDATE,System.currentTimeMillis());
    }

    @Override
    public void requestPlaces(EntityProvider<Place> provider) {
        if(isDataValid(provider.getType()))
            provider.requestFromLocalStorage();
        else
            provider.requestFromNetwork();
    }

    @Override
    public void requestPersons(EntityProvider<Person> provider) {
        if(isDataValid(provider.getType()))
            provider.requestFromLocalStorage();
        else
            provider.requestFromNetwork();
    }

    @Override
    public void requestRoutes(EntityProvider<Route> provider) {
        if(isDataValid(provider.getType()))
            provider.requestFromLocalStorage();
        else
            provider.requestFromNetwork();
    }



}
