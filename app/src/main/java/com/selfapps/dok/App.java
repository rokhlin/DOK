package com.selfapps.dok;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.selfapps.dok.utils.Constants;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Route;


public class App extends Application {

        @Override
        public void onCreate() {
            super.onCreate();

            OkHttpClient okHttpClient = getOkHttpClient();

            Picasso.Builder builder = new Picasso.Builder(this);
            builder.downloader(new OkHttp3Downloader(okHttpClient));
            Picasso built = builder.build();
            built.setIndicatorsEnabled(true); //TODO remove before release Indicator change to false
            built.setLoggingEnabled(true); //TODO remove before release change to false
            Picasso.setSingletonInstance(built);

        }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder clientBuilder   = new OkHttpClient.Builder()
                .authenticator(new Authenticator()
                {
                    @Override
                    public okhttp3.Request authenticate(Route route, Response response) throws IOException
                    {
                      //TODO hide credentials
//                        String credential = Credentials.basic(getString(R.string.lg1)+getString(R.string.lg2),
//                                getString(R.string.ps1)+getString(R.string.ps2));
                        String credential = Credentials.basic("kristirom29@gmail.com","Ma-1-Tp-2");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                })
                .cache(getCache(this));

        return clientBuilder.build();
    }

    private Cache getCache(Context c) {
        Cache cache = null;
        try {
            cache = new Cache( getDiskCacheDir(c), Constants.PICASSO_CACHE);
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }
        return cache;
    }

    public static File getDiskCacheDir(Context c) {
            File dir = c.getExternalCacheDir();

            if (dir == null)
                dir = c.getCacheDir();

            return dir;
        }

}
