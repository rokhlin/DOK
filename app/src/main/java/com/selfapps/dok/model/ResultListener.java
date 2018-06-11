package com.selfapps.dok.model;

import android.util.Log;

public abstract class ResultListener<T> {
   protected abstract void onSuccess(T entity);
   void onError(Throwable e){
       Log.d(ResultListener.class.getSimpleName(),"Error "+e.getMessage());
   }
}
