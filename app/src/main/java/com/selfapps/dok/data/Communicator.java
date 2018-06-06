package com.selfapps.dok.data;

import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;

import com.selfapps.dok.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by Ulike Anton on 06.06.2018.
 */

public class Communicator {

    public static boolean checkImageOnCache(String imgName) {
        //TODO Implement @see #loadImageFromCache(ImageView, String) search file from application cache
        return false; //File will be loaded from server
    }


    public static void loadImageFromCache(final ImageView container, final String imgName) {
        Picasso.get()
                .load(NetworkConstants.IMAGE_URL + imgName)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(container, new Callback() {
                    @Override
                    public void onSuccess() {}

                    @Override
                    public void onError(Exception e) {
                        //Try again online if cache failed
                        Picasso.get()
                                .load(NetworkConstants.IMAGE_URL + imgName)
                                .error(R.drawable.ic_launcher_background)
                                .into(container, new Callback() {
                                    @Override
                                    public void onSuccess() { }

                                    @Override
                                    public void onError(Exception e) {
                                        Log.v("Picasso","Could not fetch image "+e.getMessage());
                                    }
                                });
                    }
                });

    }

    public static void loadImageFromUrl(ImageView logo, String imgName) {
        if(logo != null)
          Picasso.get().load(NetworkConstants.IMAGE_URL + imgName)
                //.resize(50, 50)
                .centerCrop(Gravity.CLIP_HORIZONTAL)
               // .centerCrop()
                .into(logo);
    }


}
