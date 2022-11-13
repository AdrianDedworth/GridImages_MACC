package com.example.gridimages_macc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    ArrayList<Integer> myImageIds = new ArrayList<>(Arrays.asList(
            R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4,
            R.drawable.f5, R.drawable.f6, R.drawable.f7, R.drawable.f8,
            R.drawable.f9, R.drawable.f10, R.drawable.f11, R.drawable.f12
    ));

    GridView grdMuestra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grdMuestra = findViewById(R.id.myGrid);
        grdMuestra.setAdapter(new ImageAdapter(myImageIds, this));
        grdMuestra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int item_pos = myImageIds.get(position);
                ShowDialogBox(item_pos);//showDialog(position);//
            }
        });
    }

    public void ShowDialogBox(final int item_pos){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_macc);
        //Getting custom dialog views
        TextView image_name = dialog.findViewById(R.id.txt_image_name);
        ImageView image = dialog.findViewById(R.id.img);
        Button btn_fullSreen = dialog.findViewById(R.id.btn_fullScreen);
        Button btn_close = dialog.findViewById(R.id.btn_close);
        ImageView image_full = dialog.findViewById(R.id.img_fullScreen);
        String title = getResources().getResourceName(item_pos);
        //Extracting name
        int index = title.indexOf("/");
        String name = title.substring(index+1, title.length());
        image_name.setText(name);
        image.setImageResource(item_pos);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_fullSreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VistaCompleta.class);
                intent.putExtra("img_id", item_pos);
                startActivity(intent);
            }
        });

        dialog.show();
    }
}