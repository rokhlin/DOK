package com.selfapps.dok;

import android.app.Application;
import android.content.Context;


public class App extends Application {
    private static final String TAG = "App";
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        OkHttpClient okHttpClient = getOkHttpClient();
//
//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttp3Downloader(okHttpClient));
//        Picasso built = builder.build();
//        built.setIndicatorsEnabled(true); //TODO remove before release Indicator change to false
//        built.setLoggingEnabled(true); //TODO remove before release change to false
//        Picasso.setSingletonInstance(built);

    }


    public static Context getContext() {
        return context;
    }

//    private OkHttpClient getOkHttpClient() {
//        OkHttpClient.Builder clientBuilder   = new OkHttpClient.Builder()
//                .authenticator(new Authenticator()
//                {
//                    @Override
//                    public okhttp3.Request authenticate(Route route, Response response) throws IOException
//                    {
//                      //TODO hide credentials
////                        String credential = Credentials.basic(getString(R.string.lg1)+getString(R.string.lg2),
////                                getString(R.string.ps1)+getString(R.string.ps2));
//                        String credential = Credentials.basic("kristirom29@gmail.com","Ma-1-Tp-2");
//                        return response.request().newBuilder()
//                                .header("Authorization", credential)
//                                .cacheControl(getCacheControl())
//                                .build();
//                    }
//                }).cache(getCache(this));
//
//        return clientBuilder.build();
//    }

//    private CacheControl getCacheControl() {
//        return new  CacheControl.Builder()
//                .maxAge(Constants.PICASSO_CACHE_AGE_IN_HOURS, TimeUnit.HOURS)
//                .maxStale(Constants.PICASSO_CACHE_AGE_IN_HOURS, TimeUnit.HOURS)
//                .build();
//    }
//
//    private Cache getCache(Context c) {
//        Cache cache = null;
//        try {
//            cache = new Cache( getDiskCacheDir(c), Integer.MAX_VALUE);//Constants.PICASSO_CACHE
//        } catch (Exception e) {
//            Log.e("OKHttp", "Could not create http cache", e);
//        }
//        return cache;
//    }

//    public static File getDiskCacheDir(Context c) {
//            File dir = c.getExternalCacheDir();
//
//            if (dir == null){
//                Log.d(TAG,"!!!!!! External cache directory failed");
//                dir = c.getCacheDir();
//            }
//            if (dir == null)
//                Log.d(TAG,"!!!!!! Local cache directory failed");
//
//
//            return dir;
//        }

}
