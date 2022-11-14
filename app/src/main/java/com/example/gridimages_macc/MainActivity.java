package com.example.gridimages_macc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity{

//    ArrayList<Integer> myImageIds = new ArrayList<>();
    private List<ItemEntity> myImageList;
    EditText edtBuscarImagen;
    Button btnBuscar;
    GridView grdMuestra;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grdMuestra = findViewById(R.id.myGrid);
        edtBuscarImagen = findViewById(R.id.edt_imagen_buscar);
        btnBuscar = findViewById(R.id.btn_buscar);
        requestQueue = VolleySingleton.getMyInstance(this).getRequestQueue();

        myImageList = new ArrayList<>();

//        fetchData("");

        grdMuestra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ItemEntity item = myImageList.get(position);
                ShowDialogBox(item);//showDialog(position);//
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData(edtBuscarImagen.getText().toString());
            }
        });
    }

    public void ShowDialogBox(final ItemEntity item){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_macc);

        //Getting custom dialog views
        TextView user_name = dialog.findViewById(R.id.txt_user_name);
        TextView photoLikes = dialog.findViewById(R.id.txt_image_likes);
        ImageView imageDialog = dialog.findViewById(R.id.img_dialog);
        Button btn_fullSreen = dialog.findViewById(R.id.btn_fullScreen);
        Button btn_close = dialog.findViewById(R.id.btn_close);
//        ImageView image_full = dialog.findViewById(R.id.img_fullScreen);

        user_name.setText("  " + item.getUser());
        photoLikes.setText("  " + item.getLikes());
        imageDialog.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this).load(item.getImgWebURL()).into(imageDialog);

        //Extracting name
//        int index = title.indexOf("/");
//        String name = title.substring(index+1, title.length());
//        user_name.setText(name); // nombre de usuario que tomo la foto
//        image.setImageResource(item_pos);

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
                intent.putExtra("img_url", item.getImgWebURL());
                startActivity(intent);
            }
        });

        dialog.show();
    }

    private void fetchData(String imageType){
        if (imageType != "" || imageType != null){
            String subURL = imageType.replace(" ", "+");
            String url = "https://pixabay.com/api/?key=31212668-3fd8bf7d5275821a7e5d4ba24&q="+ subURL + "&image_type=photo";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        myImageList.clear();
                        JSONArray jsonArray = response.getJSONArray("hits");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String imagePrevURL = jsonObject.getString("previewURL");
                            String imageWebURL = jsonObject.getString("webformatURL");
                            int likes = jsonObject.getInt("likes");
                            String usrName = jsonObject.getString("user");

                            ItemEntity img = new ItemEntity(imagePrevURL, imageWebURL, usrName, likes);
                            myImageList.add(img);
                        }

                        ImageAdapter adapter = new ImageAdapter(myImageList, MainActivity.this);
                        grdMuestra.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(jsonObjectRequest);
        }else{
            Toast.makeText(this, "No se específico ninguna busqueda", Toast.LENGTH_SHORT).show();
        }
    }
}