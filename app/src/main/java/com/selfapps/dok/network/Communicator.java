package com.selfapps.dok.network;

import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.selfapps.dok.App;
import com.selfapps.dok.GlideApp;
import com.selfapps.dok.R;
import com.selfapps.dok.model.entity.Person;
import com.selfapps.dok.model.entity.Place;
import com.selfapps.dok.model.entity.Route;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.selfapps.dok.network.NetworkConstants.BASE_URL;

/**
 * Created by Ulike Anton on 06.06.2018.
 */

public class Communicator {
    private static Retrofit retrofit = null;
    private static  HttpLoggingInterceptor interceptor = null;
    private static final String AUTHORIZATION = "Basic a3Jpc3Rpcm9tMjlAZ21haWwuY29tOk1hLTEtVHAtMg==";

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

//    public static boolean checkImageOnCache(String imgName) {
//        //TODO Implement @see #loadImageFromCache(ImageView, String) search file from application cache
//        return false; //File will be loaded from server
//    }


//    public static void loadImageFromCache(final ImageView container, final String imgName) {
//        Picasso.get()
//                .load(NetworkConstants.IMAGE_URL + imgName)
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(container, new Callback() {
//                    @Override
//                    public void onSuccess() {}
//
//                    @Override
//                    public void onError(Exception e) {
//                        Log.d("Picasso","Could not fetch image "+e.getMessage());
//                        //Try again online if cache failed
//                        Picasso.get()
//                                .load(NetworkConstants.IMAGE_URL + imgName)
//                                .error(R.drawable.ic_launcher_background)
//                                .into(container, new Callback() {
//                                    @Override
//                                    public void onSuccess() { }
//
//                                    @Override
//                                    public void onError(Exception e) {
//                                        Log.v("Picasso","Could not fetch image "+e.getMessage());
//                                    }
//                                });
//                    }
//                });
//
//    }

    public static void loadUsingGlide(final ImageView container, final String imgName){
        GlideApp.with(App.getContext())
                .load(getUrlWithHeaders(NetworkConstants.IMAGE_URL + imgName))
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(container);

    }




    static GlideUrl getUrlWithHeaders(String url){
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", AUTHORIZATION)
                .build());
    }

//    public static void loadImageFromCacheTest(final ImageView container, final String imgName) {
//        Picasso.get()
//                .load(new File(Utils.getDiskCacheDir(App.getContext()).getPath()+"/"+imgName))
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(container, new Callback() {
//                    @Override
//                    public void onSuccess() {}
//
//                    @Override
//                    public void onError(Exception e) {
//                        Log.d("Picasso","Could not fetch image "+e.getMessage());
//                        //Try again online if cache failed
//                    }
//                });
//
//    }

//
//    public static void loadImageFromUrl(ImageView logo, String imgName) {
//        if(logo != null)
//          Picasso.get().load(NetworkConstants.IMAGE_URL + imgName)
//                //.resize(50, 50)
//                .centerCrop(Gravity.CLIP_HORIZONTAL)
//               // .centerCrop()
//                .into(logo);
//    }



    public static void getPersons(final retrofit2.Callback<ArrayList<Person>> callback) {
        getRetrofitClient().getPersons(getAuthToken()).enqueue(callback);
    }

    public static void getPlace(final retrofit2.Callback<ArrayList<Place>> callback) {
        getRetrofitClient().getPoi(getAuthToken()).enqueue(callback);
    }

//    public static void getPlaceString(final retrofit2.Callback<String> callback) {
//        getRetrofitClient().getPoiString(getAuthToken()).enqueue(callback);
//    }

    public static void getRoute(final retrofit2.Callback<ArrayList<Route>> callback) {
        getRetrofitClient().getRoutes(getAuthToken()).enqueue(callback);
    }


    public static String getAuthToken() {
        byte[] data = new byte[0];
        try {
            data = ("kristirom29@gmail.com" + ":" + "Ma-1-Tp-2").getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
    }

//    class MyAcyncTAsk extends AsyncTask<Void, Void, String> {
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            String res = "";
//            String url = POI_URL;
//            final String user = "kristirom29@gmail.com";
//            final String password = "Ma-1-Tp-2";
//            String userPass = user+":"+password;
//            try {
//                Authenticator.setDefault(new Authenticator(){
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(user,password.toCharArray());
//                    }});
//                HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
//                c.setUseCaches(false);
//                c.setRequestProperty("Authorization", "basic " +
//                        Base64.encode(userPass.getBytes(), Base64.NO_WRAP));
//                c.connect();
//                String line;
//                StringBuffer stringBuffer = new StringBuffer();
//                BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
//                while ((line = in.readLine()) != null) {
//                    stringBuffer.append(line).append('\n');
//                }
//                res = stringBuffer.toString();
//
//
//                in.close();
//                c.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//                res = e.getMessage();
//            }
//            return res;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            container.setText(s);
//
//        }
//    }

}
