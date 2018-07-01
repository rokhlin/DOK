package com.selfapps.dok.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.selfapps.dok.App;
import com.selfapps.dok.R;
import com.selfapps.dok.model.EntityProvider;
import com.selfapps.dok.model.ResultListener;
import com.selfapps.dok.model.SplashModel;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.utils.Converter;
import com.selfapps.dok.utils.PreferencesUtil;
import com.selfapps.dok.view.SplashView;
import com.selfapps.dok.view.activity.MainActivity;

import java.util.ArrayList;

public class SplashPresenter implements ISplashPresenter<SplashView> {
    private static final String TAG = SplashPresenter.class.getSimpleName();
    private SplashView view;
    private SplashModel model;
    private int finalFlag;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    public SplashPresenter() {
        this.model = new SplashModel();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (finalFlag >= 2){
                    Log.d(TAG, "Loading is finished finalFlag= " + finalFlag);
                    onLoadingFinished();
                   //debugRun();//Use it to implement test cases
                } else
                    finalFlag ++;
            }
        };
    }

    private void debugRun() {
        view.showFinishButton();
        Log.d(TAG, "Loading is finished DataType.PERSON = \n" + PreferencesUtil.getData(DataType.PERSON));
        Log.d(TAG, "Loading is finished DataType.ROUTE = \n" + PreferencesUtil.getData(DataType.ROUTE));

    }

    @Override
    public boolean isValidVersion() {
        return false;
    }


    @Override
    public void attachView(SplashView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void destroy() {
        detachView();
        handler = null;
    }

    @Override
    public void viewIsReady() {
        finalFlag = 0;
        checkConnection();

        view.updateProgress(true);
        view.setText( PreferencesUtil.getData(DataType.PERSON));
        model.requestPersons(new EntityProvider<Person>(DataType.PERSON, new ResultListener<ArrayList<Person>>() {
            @Override
            protected void onSuccess(ArrayList<Person> entity) {
                view.setText("Person update");
                //view.setText(entity.toString());
                PreferencesUtil.saveData(DataType.PERSON, Converter.entityToString(entity));
                view.postProgress(finalFlag);
                handler.sendEmptyMessage(1);
            }
        }));

        model.requestPlaces(new EntityProvider<Place>(DataType.PLACE, new ResultListener<ArrayList<Place>>() {
            @Override
            protected void onSuccess(ArrayList<Place> entity) {
                view.setText("Place update");
                //view.setText(entity.toString());
                PreferencesUtil.saveData(DataType.PLACE, Converter.entityToString(entity));
                view.postProgress(finalFlag);
                handler.sendEmptyMessage(1);
            }
        }));

        model.requestRoutes(new EntityProvider<Route>(DataType.ROUTE, new ResultListener<ArrayList<Route>>() {
            @Override
            protected void onSuccess(ArrayList<Route> entity) {
                view.setText("Route update");
                view.setText(entity.toString());
                PreferencesUtil.saveData(DataType.ROUTE, Converter.entityToString(entity));
                view.postProgress(finalFlag);
                handler.sendEmptyMessage(1);
            }
        }));
    }


    private void onLoadingFinished() {
        view.updateProgress(false);
        view.startMainActivity();
    }



    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public void checkConnection(){
        if(!isOnline()){
            Toast.makeText(App.getContext(), R.string.internet_not_available, Toast.LENGTH_SHORT).show();
            view.showFinishButton();
        }
    }

}
