package com.selfapps.dok.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.selfapps.dok.MyClickListener;
import com.selfapps.dok.R;
import com.selfapps.dok.model.ExpListAdapter;
import com.selfapps.dok.model.entity.ExpListGroup;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Tag;
import com.selfapps.dok.presenter.AdditionalScreenPresenter;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.view.IAdditionalScreenView;

import java.util.ArrayList;

public class AdditionalActivity extends AppCompatActivity implements IAdditionalScreenView {
    private String type;
    private String id;
    private AdditionalScreenPresenter presenter;
    private ArrayList<ExpListGroup> groups = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional);

        id = getIntent().getStringExtra(Constants.CONTENT_ID_TAG);
        type = getIntent().getStringExtra(Constants.CONTENT_TYPE_TAG);
        ExpandableListView listView = (ExpandableListView)findViewById(R.id.exListView);

        presenter = new AdditionalScreenPresenter(this);
        presenter.viewIsReady();


        ExpListAdapter adapter = new ExpListAdapter(groups, new MyClickListener() {
            @Override
            public void onClick(Tag tag) {
                switch (tag.type){
                    case "image":
                        presenter.onImageSelected(tag.data);
                        break;
                    case "person":
                        presenter.onPersonSelected(tag.data);
                        break;
                    case "place":
                        presenter.onPlaceSelected(tag.data);
                        break;
                }
            }
        });
        listView.setAdapter(adapter);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void showImages(ArrayList<String> imageList) {
        ExpListGroup group = new ExpListGroup();
        group.setImageList(imageList);
        groups.add(group);
    }

    @Override
    public void showPersons(ArrayList<Person> persons) {
        ExpListGroup group = new ExpListGroup();
        group.setPersons(persons);
        groups.add(group);
    }

    @Override
    public void showPlaces(ArrayList<Place> places) {
        ExpListGroup group = new ExpListGroup();
        group.setPlaces(places);
        groups.add(group);
    }

    @Override
    public void showImage(String path) {
        Intent intent = new Intent(this,FullscreenActivity.class);
        intent.putExtra(Constants.CONTENT_IMAGE_PATH_TAG,path);
        startActivity(intent);
    }

    @Override
    public void showPerson(String id) {
        Intent intent = new Intent(this, PersonDetailActivity.class);
        intent.putExtra(Constants.CONTENT_ID_TAG, id);
        startActivity(intent);

    }

    @Override
    public void showPlace(String id) {
//        Intent intent = new Intent(this, PlacesDetailActivity.class);
//        intent.putExtra(Constants.CONTENT_ID_TAG, id);
//        startActivity(intent);
    }
}
