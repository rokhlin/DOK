package com.selfapps.dok.utils;

import android.content.Context;

import java.io.File;

public class Utils {

    public static File getDiskCacheDir(Context c) {
        File dir = c.getExternalCacheDir();

        if (dir == null)
            dir = c.getCacheDir();

        return dir;
    }
}
