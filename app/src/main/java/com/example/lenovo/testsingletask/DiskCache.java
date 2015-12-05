package com.example.lenovo.testsingletask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lenovo on 2015/12/4.
 */
public class DiskCache implements I_ImageCache{
    String cacheDir= Environment.getExternalStorageDirectory()+"/cache/";
    public Bitmap get(String url){
        String finalUrl=url.substring(url.length()-19,url.length());
       return BitmapFactory.decodeFile(cacheDir+finalUrl);
    }
    public void put(String url,Bitmap bitmap){
        String finalUrl=url.substring(url.length()-19,url.length());
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream=new FileOutputStream(cacheDir+finalUrl);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
