package com.selfapps.dok.view.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.selfapps.dok.R;
import com.selfapps.dok.network.Communicator;
import com.selfapps.dok.utils.Constants;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        getSupportActionBar().hide();

        String imgPath = getIntent().getStringExtra(Constants.CONTENT_IMAGE_PATH_TAG);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        //photoView.setImageResource(R.drawable.image);
        Communicator.loadUsingGlide(photoView,imgPath,R.drawable.place_holder);

    }

}
