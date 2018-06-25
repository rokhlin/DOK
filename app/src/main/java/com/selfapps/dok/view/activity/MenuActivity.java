package com.selfapps.dok.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.selfapps.dok.R;
import com.selfapps.dok.utils.Constants;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        LinearLayout containerPlaces = findViewById(R.id.container_places);
        LinearLayout containerPersons = findViewById(R.id.container_persons);
        LinearLayout containerRoutes= findViewById(R.id.container_routes);
        ImageView search = findViewById(R.id.btn_search);
        containerPersons.setOnClickListener(this);
        containerPlaces.setOnClickListener(this);
        containerRoutes.setOnClickListener(this);
        search.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent =null;
        switch (v.getId()){
            case R.id.container_places:
                intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra(Constants.CONTENT_TAB_ID,0);
                break;
            case R.id.container_persons:
                intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra(Constants.CONTENT_TAB_ID,1);
                break;
            case R.id.container_routes:
                intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra(Constants.CONTENT_TAB_ID,2);
                break;
            case R.id.btn_search:
                intent = new Intent(MenuActivity.this, SearchActivity.class);
                break;
            default:
                intent = new Intent(MenuActivity.this,MainActivity.class);
        }
        startActivity(intent);
    }
}