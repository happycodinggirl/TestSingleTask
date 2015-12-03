package com.example.lenovo.testsingletask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by lenovo on 2015/12/3.
 */
public class ImageAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ImageLoader imageLoader;

    public ImageAdapter(Context context) {
        this.context=context;
        imageLoader=new ImageLoader();
        inflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return Images.imageThumbUrls.length;
    }

    @Override
    public Object getItem(int position) {
        return Images.imageThumbUrls[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item,null);
            holder=new ViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        imageLoader.displayImage(holder.imageView, (String) getItem(position));

        return convertView;
    }

    static class  ViewHolder{
        ImageView imageView;

    }
}
