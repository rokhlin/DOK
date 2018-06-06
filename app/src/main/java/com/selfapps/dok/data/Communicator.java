package com.selfapps.dok.data;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Ulike Anton on 06.06.2018.
 */

public class Communicator {

    public static boolean checkImageOnCache(String imgName) {
        //TODO Implement @see #loadInageFromCache(ImageView, String) search file from application cache
        return false; //File will be loaded from server
    }


    public static void loadInageFromCache(ImageView logo, String imgName) {
        //TODO Implement search file from application cache
    }

    public static void loadImageFromUrl(ImageView logo, String imgName) {
        if(logo != null)
          Picasso.get().load(NetworkConstants.BASE_URL)
                .resize(50, 50)
                .centerCrop()
                .into(logo);
    }
}
