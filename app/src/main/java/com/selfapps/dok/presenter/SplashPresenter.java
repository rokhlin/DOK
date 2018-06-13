package com.selfapps.dok.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

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

import java.util.ArrayList;

public class SplashPresenter implements ISplashPresenter<SplashView> {
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
                if (finalFlag >= 2)
                    onLoadingFinished();
                else
                    finalFlag ++;
            }
        };
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

        model.requestPlaces(new EntityProvider<Place>(DataType.POI, new ResultListener<ArrayList<Place>>() {
            @Override
            protected void onSuccess(ArrayList<Place> entity) {
                view.setText("Place update");
                //view.setText(entity.toString());
                PreferencesUtil.saveData(DataType.POI, Converter.entityToString(entity));
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


      //  onLoadingFinished();

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){ //start loop
//                    if (!(finalFlag < 3)) break;
//                }
//
//                //onFinish
//                Looper.prepare();
//                onLoadingFinished();
//                Looper.loop();
//            }
//        });
//        thread.start();
    }


    private void onLoadingFinished() {
        view.updateProgress(false);
        view.startMainActivity();
//        Handler mainHandler = new Handler(Looper.getMainLooper());
//        Runnable myRunnable = new Runnable() {
//            @Override
//            public void run() {
//                //TODO Create behavior on load end
//
//                view.updateProgress(false);
//                view.startMainActivity();
//
//            }
//        };
//
//        mainHandler.post(myRunnable);
    }





}
