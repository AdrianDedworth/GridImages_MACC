package com.example.gridimages_macc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class VistaCompleta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_completa);
        getSupportActionBar().hide();

        ImageView imageFullScreen = findViewById(R.id.img_fullScreen);

        String img_url = getIntent().getExtras().getString("img_url");

        Glide.with(this).load(img_url).into(imageFullScreen);
    }
}