package com.selfapps.dok.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selfapps.dok.R;
import com.selfapps.dok.model.RVRoutesAdapter;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.utils.Converter;
import com.selfapps.dok.utils.PreferencesUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoutesFragment extends Fragment {


    public RoutesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView rv = rootView.findViewById(R.id.recycler_view);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new RVRoutesAdapter(getContent()));
        rv.hasFixedSize();

        return rootView;
    }

    private ArrayList<Route> getContent() {
        return Converter.getRoutesFromString(PreferencesUtil.getData(DataType.ROUTE));
    }

}
