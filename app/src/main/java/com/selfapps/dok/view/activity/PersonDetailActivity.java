package com.selfapps.dok.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfapps.dok.R;
import com.selfapps.dok.model.PersonsDetailModelI;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Location;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.presenter.PersonsDetailPresenter;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.view.DetailsView;

import java.util.ArrayList;
import java.util.Objects;

public class PersonDetailActivity extends AppCompatActivity implements DetailsView{

    private PersonsDetailPresenter presenter;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private ImageView logo;
    private TextView firstLetter;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        String id = getIntent().getStringExtra(Constants.CONTENT_ID_TAG);

        presenter = new PersonsDetailPresenter(new PersonsDetailModelI(id, DataType.PERSON),id);
        presenter.attachView(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logo = findViewById(R.id.iv_logo);
        firstLetter = findViewById(R.id.tv_first_letter);
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
       firstLetter.setText(getFirstLetter(name));
    }

    private String getFirstLetter(String name) {
        return name.substring(0,1).toUpperCase();
    }

    @Override
    public void showMap(Location location) {
        //Don't used here
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
