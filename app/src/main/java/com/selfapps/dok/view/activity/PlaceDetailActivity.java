package com.selfapps.dok.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfapps.dok.R;
import com.selfapps.dok.model.PlacesDetailModel;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.presenter.PlaceDetailPresenter;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.view.DetailsView;

import java.util.ArrayList;
import java.util.Objects;

public class PlaceDetailActivity extends AppCompatActivity implements DetailsView {
    private PlaceDetailPresenter presenter;
    private FloatingActionButton fab;
    private ImageView logo;
    private Button showMap;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);


        String id = getIntent().getStringExtra(Constants.CONTENT_ID_TAG);

        presenter = new PlaceDetailPresenter(new PlacesDetailModel(id, DataType.PLACE),id);
        presenter.attachView(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logo = findViewById(R.id.iv_logo);
        showMap = findViewById(R.id.btn_show_map);
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
        return null; //Don't used here
    }

    @Override
    public void showTextContent(String content) {
        info.setText(content);
    }

    @Override
    public void showPlacesContent(ArrayList<Place> places) {
        //Don't used here
    }

    @Override
    public void showPersonsContent(ArrayList<Person> persons) {
        //Don't used here
    }

    @Override
    public void showName(String name) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(name);
    }


    @Override
    public void showMap(Location location) {
        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMapSelected();
            }
        });
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
