package com.selfapps.dok.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfapps.dok.R;
import com.selfapps.dok.model.entity.Entity;
import com.selfapps.dok.presenter.SplashPresenter;
import com.selfapps.dok.view.SplashView;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements SplashView {
    private Button clear, update;
    private TextView container;
    private ImageView imageView;

    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        presenter = new SplashPresenter();
        presenter.attachView(this);

        container = (TextView) findViewById(R.id.tv_text);
        imageView = (ImageView) findViewById(R.id.img_test);
        update = (Button) findViewById(R.id.btn_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.viewIsReady();
            }
        });
        clear = (Button) findViewById(R.id.btn_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.setText("");
            }
        });

        presenter.viewIsReady();
    }



    @Override
    public void updateProgress(boolean isActive) {
        if(isActive){
            container.setText("loading...");
        }else {
            container.setText("Loading Finished");
        }
    }

    @Override
    public void postProgress(int progress) {
        int step = 100/3;
        container.setText(container.getText()+"\n"+"finished: "+step*progress +"%");
    }

    @Override
    public void setText(String text) {
        container.setText(container.getText()
                +"\n"
                +text);
    }

    @Override
    public void startMainActivity() {

    }

    @Override
    public void showList(List<Entity> entities) {

    }
}
