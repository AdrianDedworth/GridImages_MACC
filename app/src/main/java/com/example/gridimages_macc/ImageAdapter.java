package com.example.gridimages_macc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private List<ItemEntity> imgItem;
    private Context myContext;

    public ImageAdapter(List<ItemEntity> imgItem, Context myContext){
        this.imgItem = imgItem;
        this.myContext = myContext;
    }

    @Override
    public int getCount() {
        return imgItem.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView = (ImageView) view;
//
//        if (imageView == null){
//            imageView = new ImageView(myContext);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(350, 450));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        }
//        imageView.setImageResource(myThumbIds.get(position));
//
//        return imageView;
        ItemEntity itemEntity = imgItem.get(position);

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(myContext.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_image, null);

            ImageView img = convertView.findViewById(R.id.img_grid_item);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(myContext).load(itemEntity.getImgPrevURL()).into(img);
        }
        return convertView;
    }
}
