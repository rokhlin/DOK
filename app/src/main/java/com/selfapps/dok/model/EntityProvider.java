package com.selfapps.dok.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.utils.Converter;
import com.selfapps.dok.utils.PreferencesUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntityProvider<T> {
    private DataType type;
    private String TAG;
    private ResultListener<ArrayList<T>> listener;
    private Gson gson;

    public EntityProvider(DataType type, ResultListener<ArrayList<T>> listener) {
        this.type = type;
        TAG = "Provider "+type;
        this.listener = listener;
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public DataType getType() {
        return type;
    }

    public void requestFromLocalStorage() {
        Log.d(TAG,"request Data From Local Storage.");
        try {
            String data = PreferencesUtil.getData(type);
            listener.onSuccess((ArrayList<T>) Converter.getEntityFromString(type,data));
        } catch (UnsupportedOperationException e) {
            listener.onError(e);
        }
    }

    public void requestFromNetwork() {
        Log.d(TAG,"request Data From Network.");
        try {
            switch (type){
                case PERSON:
                    Communicator.getPersons(new Callback<ArrayList<Person>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                            if(response.isSuccessful()){
                                listener.onSuccess((ArrayList<T>) response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                            listener.onError(t);
                        }
                    });
                    break;
                case PLACE:
                    Communicator.getPlace(new Callback<ArrayList<Place>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Place>> call, Response<ArrayList<Place>> response) {
                            if(response.isSuccessful()){
                                listener.onSuccess((ArrayList<T>) response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Place>> call, Throwable t) {
                            listener.onError(t);
                        }
                    });
                    break;
                case ROUTE:
                    Communicator.getRoute(new Callback<ArrayList<Route>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Route>> call, Response<ArrayList<Route>> response) {

                            if(response.isSuccessful()){
                                persistiateResult((ArrayList<T>) response.body());

                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Route>> call, Throwable t) {
                            listener.onError(t);
                        }
                    });
                    break;
            }
            //Set last updated time
            PreferencesUtil.setLong(Constants.PREF_LAST_UPDATE,System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void persistiateResult(ArrayList<T> body) {
        listener.onSuccess(body);
        PreferencesUtil.saveData(type, getStringFromObject(body));
    }

    private String getStringFromObject(ArrayList<T> body) {
        return gson.toJson(body);
    }


}