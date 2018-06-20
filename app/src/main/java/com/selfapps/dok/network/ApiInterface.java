package com.selfapps.dok.network;

import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

import static com.selfapps.dok.network.NetworkConstants.PERSON_URL;
import static com.selfapps.dok.network.NetworkConstants.POI_URL;
import static com.selfapps.dok.network.NetworkConstants.ROUTE_URL;

public interface ApiInterface {

    @GET(POI_URL)
    Call<ArrayList<Place>> getPoi(
                    @Header("Authorization") String authkey);

    @GET(ROUTE_URL)
    Call<ArrayList<Route>> getRoutes(
            @Header("Authorization") String authkey);

    @GET(PERSON_URL)
    Call<ArrayList<Person>> getPersons(
            @Header("Authorization") String authkey);
}
