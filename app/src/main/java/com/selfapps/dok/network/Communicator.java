package com.selfapps.dok.network;

import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import com.selfapps.dok.App;
import com.selfapps.dok.GlideApp;
import com.selfapps.dok.R;
import com.selfapps.dok.model.entity.DataType;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;
import com.selfapps.dok.utils.PreferencesUtil;
import com.selfapps.dok.utils.SepiaFilterTransformation;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.selfapps.dok.GlideOptions.bitmapTransform;
import static com.selfapps.dok.network.NetworkConstants.BASE_URL;

/**
 * Created by Ulike Anton on 06.06.2018.
 */

public class Communicator {
    private static Retrofit retrofit = null;
    private static  HttpLoggingInterceptor interceptor = null;

    public static ApiInterface getRetrofitClient() {

        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }


    public static void loadImageFilterSepia(final ImageView container, final String imgName, int placeholder){
        GlideApp.with(App.getContext())
                .load(getUrlWithHeaders(NetworkConstants.IMAGE_URL + imgName))
                .placeholder(placeholder)
                .apply(bitmapTransform(new SepiaFilterTransformation()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(container);
    }

    public static void loadImage(final ImageView container, final String imgName, int placeholder){
        GlideApp.with(App.getContext())
                .load(getUrlWithHeaders(NetworkConstants.IMAGE_URL + imgName))
                .placeholder(placeholder)
               // .apply(bitmapTransform(new SepiaFilterTransformation()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(container);
    }

    static GlideUrl getUrlWithHeaders(String url){
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader(App.getContext().getString(R.string.au_full),
                         App.getContext().getString(R.string.basic0)+" "
                        +App.getContext().getString(R.string.basic1)
                        +App.getContext().getString(R.string.basic2))
                .build());
    }

    public static void getPersons(final retrofit2.Callback<ArrayList<Person>> callback) {
        getRetrofitClient().getPersons(getAuthToken()).enqueue(callback);
    }

    public static void getPlace(final retrofit2.Callback<ArrayList<Place>> callback) {
        getRetrofitClient().getPoi(getAuthToken()).enqueue(callback);
    }

    public static void getRoute(final retrofit2.Callback<ArrayList<Route>> callback) {
        getRetrofitClient().getRoutes(getAuthToken()).enqueue(callback);
    }

    public static String getAuthToken() {
        byte[] data = new byte[0];
        try {
            data = (App.getContext().getResources().getString(R.string.lg1)
                    + App.getContext().getResources().getString(R.string.lg2)
                    + ":"
                    + App.getContext().getResources().getString(R.string.ps1)
                    + App.getContext().getResources().getString(R.string.ps2))
                    .getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  App.getContext().getString(R.string.basic0)+" " + Base64.encodeToString(data, Base64.NO_WRAP);
    }

}
