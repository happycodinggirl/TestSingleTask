package com.example.lenovo.testsingletask;

import android.graphics.Bitmap;

/**
 * Created by lenovo on 2015/12/5.
 */
public interface I_ImageCache {
    public void put(String url,Bitmap bitmap);
    public Bitmap get(String url);
}
