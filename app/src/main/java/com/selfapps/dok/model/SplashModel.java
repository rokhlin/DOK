package com.selfapps.dok.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.utils.PreferencesUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashModel implements ISplashModel {

    public SplashModel() {

    }

    @Override
    public boolean isDataValid(DataType type) {
        return !PreferencesUtil.getData(type).isEmpty() && PreferencesUtil.getLong(Constants.PREF_LAST_UPDATE,0) > getExpiredTime();
    }

    private long getExpiredTime() {
        return System.currentTimeMillis() - Constants.UPDATE_PERIOD_MILLS ;
    }

    private void setDataUpdatedTime() {
        PreferencesUtil.setLong(Constants.PREF_LAST_UPDATE,System.currentTimeMillis());
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
