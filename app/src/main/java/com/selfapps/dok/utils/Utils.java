package com.selfapps.dok.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    private static final String TAG ="Utils" ;

    public static File getDiskCacheDir(Context c) {
//        File dir = c.getExternalCacheDir();
//
//        if (dir == null)
          File  dir = c.getCacheDir();

        return dir;
    }


    public static HashSet<String> extractImagesTo(String str) {
        HashSet<String> resSet = new HashSet<>();

        if(str.isEmpty()) return resSet;
        int lastIndex = 0;
        String startFlag =  "\"imageList\":[";
        String endFlag = "],";


        while (lastIndex >= 0){
            try {
                int beginIndex = str.indexOf(startFlag,lastIndex);
                int endIndex = str.indexOf(endFlag,beginIndex +13);

                if(beginIndex < 0 || endIndex < 0 ) break;//Exit check

                String newStr = str.substring(beginIndex + 13 ,endIndex);
                String cleanStr = newStr.trim()
                        .replace("\"","");

                String[] res = cleanStr.split(",");

                if(res.length < 1) continue;

                resSet.addAll(Arrays.asList(res));


                lastIndex = endIndex;
            } catch (StringIndexOutOfBoundsException e) {
                Log.d(TAG, " End of file. " + e.getMessage());
               break;
            }
        }
        return resSet;
    }

    public static ArrayList<String> getListOfFiles(@NonNull File cacheDir) {
        File[] files = cacheDir.listFiles();
        String[] fileNames = new String[files.length];
        Log.d("Files", "Size: "+ files.length);

        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getName();
        }

        return new ArrayList<>(Arrays.asList(fileNames));
    }
}
