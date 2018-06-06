package com.selfapps.dok.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selfapps.dok.R;
import com.selfapps.dok.model.RVAdapter;
import com.selfapps.dok.model.entity.Place;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFragment extends Fragment {
    ArrayList<Place> places;

    public PlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_places, container, false);
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new RVAdapter(getPlaces()));
        return rootView;
    }

    private ArrayList<Place> getPlaces() {
        ArrayList<Place> places = new ArrayList<>();
        //TODO replace with request from repository
        return places;
    }

}
