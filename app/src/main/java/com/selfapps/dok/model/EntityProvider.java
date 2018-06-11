package com.selfapps.dok.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.utils.PreferencesUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntityProvider<T> {
    private DataType type;
    private ResultListener<ArrayList<T>> listener;
    private Gson gson;

    public EntityProvider(DataType type, ResultListener<ArrayList<T>> listener) {
        this.type = type;
        this.listener = listener;
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public DataType getType() {
        return type;
    }

    public void requestFromLocalStorage() {
        try {
            String data = PreferencesUtil.getData(type);
            listener.onSuccess(getObjectFromString(data));
        } catch (UnsupportedOperationException e) {
            listener.onError(e);
        }
    }

    public ArrayList<T> getObjectFromString(String data) throws UnsupportedOperationException {
        Type collectionType = new TypeToken<ArrayList<T>>(){}.getType();
        return gson.fromJson(data, collectionType);

    }

    public void requestFromNetwork() {
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
                case POI:
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
            }

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