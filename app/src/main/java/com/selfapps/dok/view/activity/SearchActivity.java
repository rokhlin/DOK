package com.selfapps.dok.view.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.selfapps.dok.MyClickListener;
import com.selfapps.dok.R;
import com.selfapps.dok.model.ExpListAdapter;
import com.selfapps.dok.model.entity.Entity;
import com.selfapps.dok.model.entity.ExpListGroup;
import com.selfapps.dok.model.entity.Tag;
import com.selfapps.dok.presenter.SearchPresenter;
import com.selfapps.dok.utils.Constants;
import com.selfapps.dok.utils.PreferencesUtil;
import com.selfapps.dok.view.ISearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ISearchView {

    private static final String TAG = SearchActivity.class.getSimpleName();
    private SearchPresenter presenter;
   // private SearchView searchView;
    private ExpandableListView listView;
    private ExpListAdapter adapter;
    private ArrayList<ExpListGroup> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setSearchView();

        presenter = new SearchPresenter(this);

        listView = (ExpandableListView)findViewById(R.id.exListView);

        adapter = new ExpListAdapter(new ArrayList<ExpListGroup>(), new MyClickListener() {
            @Override
            public void onClick(Tag tag) {
                if(tag ==null) return;

                Log.d(TAG, "onClick: tag="+tag.toString());
                switch (tag.type){
                    case "ROUTE":
                        presenter.onRouteSelected(tag.data);
                        break;
                    case "PERSON":
                        presenter.onPersonSelected(tag.data);
                        break;
                    case "PLACE":
                        presenter.onPlaceSelected(tag.data);
                        break;
                }
            }
        });
        listView.setAdapter(adapter);
    }

    private void setSearchView() {
        ActionBar actionBar = getSupportActionBar();
        SearchView searchView = new SearchView(this);
        actionBar.setCustomView(searchView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        searchView.setQuery("",true);
        searchView.setFocusable(true);
        searchView.setIconified(true);
        searchView.requestFocusFromTouch();
        searchView.setSubmitButtonEnabled(false);
        searchView.setOnQueryTextListener(this);

        configureSearchView(searchView);
    }

    private void configureSearchView(SearchView searchView) {
        android.view.ViewGroup.LayoutParams params = searchView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        AppCompatImageView closeBtn = searchView.findViewById(R.id.search_close_btn);
        closeBtn.setImageResource(R.drawable.ic_close_accent_24dp);

        AppCompatImageView searchBtn = searchView.findViewById(R.id.search_mag_icon);
        searchBtn.setImageResource(R.drawable.ic_search_accent_24dp);

        AppCompatImageView searchBtn2 = searchView.findViewById(R.id.search_button);
        searchBtn2.setImageResource(R.drawable.ic_search_accent_24dp);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(query.length()>3)
            presenter.onTextChanged(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length()>3)
            presenter.onTextChanged(newText);
        return true;
    }

    @Override
    public void showResultList(ArrayList<ExpListGroup> resultSet) {
        groups = resultSet;
        adapter.notifyDataSetChanged(resultSet);
    }

    @Override
    public void showPerson(String id) {
        Intent intent = new Intent(this, PersonDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.CONTENT_ID_TAG, id);
        startActivity(intent);
    }

    @Override
    public void showPlace(String id) {
        Intent intent = new Intent(this, PlaceDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.CONTENT_ID_TAG, id);
        startActivity(intent);
    }

    @Override
    public void showRoute(String id) {
        Intent intent = new Intent(this, RoutesDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.CONTENT_ID_TAG, id);
        startActivity(intent);
    }

    @Override
    public void expandGroupItems() {
        for (int i = 0; i <groups.size() ; i++) {
            listView.expandGroup(i);
        }
    }
}
