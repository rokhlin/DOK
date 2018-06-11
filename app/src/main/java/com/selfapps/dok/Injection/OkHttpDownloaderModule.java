//package com.selfapps.dok.Injection;
//
//import android.util.Log;
//
//import com.selfapps.dok.utils.Constants;
//import com.squareup.picasso.OkHttp3Downloader;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.ref.WeakReference;
//
//import dagger.Module;
//import dagger.Provides;
//import okhttp3.Authenticator;
//import okhttp3.Cache;
//import okhttp3.Credentials;
//import okhttp3.OkHttpClient;
//import okhttp3.Response;
//import okhttp3.Route;
//
//@Module
//public class OkHttpDownloaderModule {
//    private WeakReference<File> cacheDir;
//
//    @Provides
//    OkHttp3Downloader getOkHttp3Downloader(OkHttpClient okHttpClient){
//        return new OkHttp3Downloader(okHttpClient);
//    }
//
//    @Provides
//    OkHttpClient getHttpClient(File cache){
//        cacheDir = new WeakReference<>(cache);
//        OkHttpClient.Builder clientBuilder   = new OkHttpClient.Builder()
//                .authenticator(new Authenticator()
//                {
//                    @Override
//                    public okhttp3.Request authenticate(Route route, Response response) throws IOException
//                    {
//                        //TODO hide credentials
////                        String credential = Credentials.basic(getString(R.string.lg1)+getString(R.string.lg2),
////                                getString(R.string.ps1)+getString(R.string.ps2));
//                        String credential = Credentials.basic("kristirom29@gmail.com","Ma-1-Tp-2");
//                        return response.request().newBuilder()
//                                .header("Authorization", credential)
//                                .build();
//                    }
//                })
//                .cache(getCache());
//
//        return clientBuilder.build();
//    }
//
//    @Provides
//    Cache getCache() {
//        Cache cache = null;
//        try {
//            if(cacheDir != null)
//            cache = new Cache(cacheDir.get(), Constants.PICASSO_CACHE);
//        } catch (Exception e) {
//            Log.e("OKHttp", "Could not create http cache", e);
//        }
//        return cache;
//    }
//
//}
