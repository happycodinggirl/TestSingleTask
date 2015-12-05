package com.example.lenovo.testsingletask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lenovo on 2015/12/3.
 */
public class ImageLoader {


    boolean useDiskCache;
    I_ImageCache i_imageCache;

    public void setI_imageCache(I_ImageCache i_imageCache) {
        this.i_imageCache = i_imageCache;
    }

    public void setUseDiskCache(boolean useDiskCache) {
        this.useDiskCache = useDiskCache;
    }

    public boolean isUseDiskCache() {
        return useDiskCache;
    }

   // ImageCache imgaCache=new ImageCache();
    //DiskCache diskCache=new DiskCache();
    ExecutorService executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public void displayImage(final ImageView imageView, final String url){
        if (i_imageCache.get(url)!=null){
            imageView.setImageBitmap(i_imageCache.get(url));
            return;
        }
        imageView.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap=downLoadImage(url);
                if (bitmap==null){
                    return;
                }
                if (imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                i_imageCache.put(url,bitmap);
            }
        });


    }

    public Bitmap downLoadImage(String url){
        Bitmap bitmap=null;
        try {
            URL url1=new URL(url);
            HttpURLConnection urlConnection= (HttpURLConnection) url1.openConnection();
            bitmap=BitmapFactory.decodeStream(urlConnection.getInputStream());
            urlConnection.disconnect();
            return  bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
