package com.selfapps.dok.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.selfapps.dok.R;
import com.selfapps.dok.presenter.SplashPresenter;
import com.selfapps.dok.view.SplashView;
import com.tubitv.ui.TubiLoadingView;

public class SplashActivity extends AppCompatActivity implements SplashView {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private TubiLoadingView progress;
    private  TextView finish;
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        finish = findViewById(R.id.tv_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                    finishAndRemoveTask();
                } else{
                    finishAffinity();
                }
                System.exit(0);
 //               Crashlytics.getInstance().crash();
            }
        });

        progress = findViewById(R.id.progress);
        presenter = new SplashPresenter();
        presenter.attachView(this);


        presenter.viewIsReady();
    }



    @Override
    public void updateProgress(boolean isActive) {
        if(isActive){
           progress.start();
        }else {
           progress.stop();
        }
    }

    @Override
    public void postProgress(int progress) {
       //Unused

    }

    @Override
    public void setText(String text) {
        //TODO check necessity
    }

    @Override
    public void startMainActivity() {
            Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
    }

    @Override
    public void showFinishButton() {
        finish.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
