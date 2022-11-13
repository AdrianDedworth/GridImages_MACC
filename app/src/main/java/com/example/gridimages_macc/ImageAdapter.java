package com.example.gridimages_macc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private List<Integer> myThumbIds;
    private Context myContext;

    public ImageAdapter(List<Integer> myThumbIds, Context myContext){
        this.myThumbIds = myThumbIds;
        this.myContext = myContext;
    }

    @Override
    public int getCount() {
        return myThumbIds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return myThumbIds.get(i);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView = (ImageView) view;

        if (imageView == null){
            imageView = new ImageView(myContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(350, 450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        imageView.setImageResource(myThumbIds.get(position));

        return imageView;
    }
}
