package com.example.lenovo.testsingletask;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by lenovo on 2015/12/3.
 */
public class ImageCache  implements I_ImageCache {
    private LruCache<String,Bitmap> imageCaches;

    public ImageCache() {
        initImageCache();
    }

    private void initImageCache(){
        final int maxSize= (int) (Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize=maxSize/4;
        imageCaches=new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getRowBytes()*value.getHeight()/1024;
            }
        };

    }

    public void put(String url,Bitmap bitmap){
        imageCaches.put(url,bitmap);
    }
    public Bitmap get(String url){
        return imageCaches.get(url);
    }
}
