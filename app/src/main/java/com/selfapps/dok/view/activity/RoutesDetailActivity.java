package com.selfapps.dok.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfapps.dok.MyClickListener;
import com.selfapps.dok.R;
import com.selfapps.dok.model.ExpListAdapter;
import com.selfapps.dok.model.PlacesDetailModel;
import com.selfapps.dok.model.RouteDetailModel;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.ExpListGroup;
import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Tag;
import com.selfapps.dok.presenter.PlaceDetailPresenter;
import com.selfapps.dok.presenter.RouteDetailPresenter;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.view.DetailsView;

import java.util.ArrayList;
import java.util.Objects;

public class RoutesDetailActivity extends AppCompatActivity implements DetailsView {
    private RouteDetailPresenter presenter;
    private FloatingActionButton fab;
    private ImageView logo;
    private TextView info;
    private ExpandableListView listView;
    private ArrayList<ExpListGroup> groups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_detail);
        String id = getIntent().getStringExtra(Constants.CONTENT_ID_TAG);

        listView = (ExpandableListView)findViewById(R.id.exListView);
        presenter = new RouteDetailPresenter(new RouteDetailModel(id, DataType.ROUTE),id);
        presenter.attachView(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logo = findViewById(R.id.iv_logo);
        info = findViewById(R.id.tv_content_detail);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAdditionalScreenSelected();
            }
        });

        presenter.viewIsReady();



    }

    @Override
    public View getImageContainer() {
        return logo;
    }

    @Override
    public GridView getGalleryContainer() {
        return null; //Don't use here
    }

    @Override
    public void showTextContent(String content) {
        info.setText(content);
    }

    @Override
    public void showPlacesContent(ArrayList<Place> places) {
        ExpListGroup group = new ExpListGroup();
        group.setPlaces(places);
        groups.add(group);

        ExpListAdapter adapter = new ExpListAdapter(groups, new MyClickListener() {
            @Override
            public void onClick(Tag tag) {

            }
        });
        listView.setAdapter(adapter);

    }

    @Override
    public void showPersonsContent(ArrayList<Person> persons) {
        //Don't use here
    }

    @Override
    public void showName(String name) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(name);
    }


    @Override
    public void showMap(Location location) {
        //Don't use here
    }

    @Override
    public void showAdditionalScreen(String id, DataType type) {
        Intent intent = new Intent(this,AdditionalActivity.class);
        intent.putExtra(Constants.CONTENT_ID_TAG, id);
        intent.putExtra(Constants.CONTENT_TYPE_TAG, type.name());
        startActivity(intent);
    }

    @Override
    public void updateListAdapter(ArrayList<Place> places) {

    }

    @Override
    public void updateFab(int img) {
        fab.setImageResource(img);
    }
}
