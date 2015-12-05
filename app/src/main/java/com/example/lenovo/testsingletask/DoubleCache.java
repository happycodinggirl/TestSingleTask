package com.example.lenovo.testsingletask;

import android.graphics.Bitmap;

/**
 * Created by lenovo on 2015/12/5.
 */
public class DoubleCache implements I_ImageCache{
    ImageCache imageCache=new ImageCache();
    DiskCache diskCache=new DiskCache();






    @Override
    public void put(String url, Bitmap bitmap) {
        imageCache.put(url,bitmap);
        diskCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap cacheBitmap=imageCache.get(url);
        if (cacheBitmap!=null){
            return cacheBitmap;
        }
        cacheBitmap=diskCache.get(url);
        return cacheBitmap;
    }
}
