package com.selfapps.dok.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.selfapps.dok.App;
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
    private LinearLayout placesContainer;
    private ArrayList<ExpListGroup> groups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_detail);
        String id = getIntent().getStringExtra(Constants.CONTENT_ID_TAG);


        presenter = new RouteDetailPresenter(new RouteDetailModel(id, DataType.ROUTE),id);
        presenter.attachView(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        placesContainer = findViewById(R.id.places_container);
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
        for (Place place : places) {
            final Location location = place.getLocation();
            LayoutInflater ltInflater = getLayoutInflater();
            View view = ltInflater.inflate(R.layout.list_item_persons, null);
            TextView name = (TextView) view.findViewById(R.id.tw_name1);
            TextView marker = (TextView) view.findViewById(R.id.tv_type_marker);
            TextView details =(TextView) view.findViewById(R.id.btn_details);
            ImageView logo = (ImageView) view.findViewById(R.id.img_photo1);
            View divider = view.findViewById(R.id.divider);

            details.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);

            logo.setImageResource(R.drawable.ic_action_map);
            //logo.getLayoutParams().height = 72;
            logo.getLayoutParams().width  = 124;
            logo.setPadding(24,8,0,8);
//            LinearLayout.LayoutParams parameter =  (LinearLayout.LayoutParams) logo.getLayoutParams();
//            parameter.setMargins(0, 0, 0, 0); // left, top, right, bottom
//            logo.setLayoutParams(parameter);
            logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            logo.requestLayout();
            //logo.setVisibility(View.GONE);

            name.setText(place.getName());
            name.setTextSize(14);
            marker.setText(place.getAddress());
            //details.setText("To map >");

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.onMapSelected(location);
                }
            });
            placesContainer.addView(view);
        }

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
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(location.getGeoUri(presenter.getName()));
        App.getContext().startActivity(mapIntent);
    }

    @Override
    public void showAdditionalScreen(String id, DataType type) {
        Intent intent = new Intent(this,AdditionalActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
