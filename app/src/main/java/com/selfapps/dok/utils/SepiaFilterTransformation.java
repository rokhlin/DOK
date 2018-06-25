package com.selfapps.dok.utils;

import android.support.annotation.NonNull;

import java.security.MessageDigest;

import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;

public class SepiaFilterTransformation extends GPUFilterTransformation {

    private static final int VERSION = 1;
    private static final String ID =
            "jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation." + VERSION;

    private float intensity;

    public SepiaFilterTransformation() {
        this(1.0f);
    }

    public SepiaFilterTransformation(float intensity) {
        super(new GPUImageSepiaFilter());
        this.intensity = intensity;
        GPUImageSepiaFilter filter = getFilter();
        filter.setIntensity(this.intensity);
    }

    @Override public String toString() {
        return "SepiaFilterTransformation(intensity=" + intensity + ")";
    }

    @Override public boolean equals(Object o) {
        return o instanceof SepiaFilterTransformation;
    }

    @Override public int hashCode() {
        return ID.hashCode() + (int) (intensity * 10);
    }

    @Override public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((ID + intensity).getBytes(CHARSET));
    }
}