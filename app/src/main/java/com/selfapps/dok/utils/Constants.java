package com.selfapps.dok.utils;

import java.util.concurrent.TimeUnit;

public class Constants {
    public static final int MEGABYTE = 1024*1024;
    public static final int PICASSO_CACHE = 150 * MEGABYTE;

    public static final String PREF_INIT_KEY = "com.selfapps.dok.app.sharedpreferences";
    public static final String PREF_LAST_UPDATE = "last_data_update";
    public static final long UPDATE_PERIOD_MILLS = TimeUnit.DAYS.toMillis(3);//Update each 3 days
    public static final int PICASSO_CACHE_AGE_IN_HOURS = 7 * 24 ;
    public static final String CONTENT_ID_TAG = "content_id_tag" ;
    public static final String CONTENT_TYPE_TAG = "content_type_tag";
    public static final String CONTENT_IMAGE_PATH_TAG =  "content_image_tag";
    public static final String CONTENT_TAB_ID = "content_tab_id";
    public static final String PREF_SPONSORED_DIALOG = "sponsored_dialog_shown";
}
