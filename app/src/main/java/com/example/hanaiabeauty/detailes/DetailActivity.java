package com.example.hanaiabeauty.detailes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.database.SqliteFavoraite;
import com.example.hanaiabeauty.databinding.ActivityDetailBinding;
import com.example.hanaiabeauty.model.Catogray;
import com.example.hanaiabeauty.model.Favoraite;
import com.example.hanaiabeauty.roomdatabase.AppDatabase;
import com.example.hanaiabeauty.roomdatabase.RoomAdpter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener// RatingDialogListener
{
    ActivityDetailBinding binding;
    Catogray dataIntent;
    Favoraite fav;
    AppDatabase database;
    RoomAdpter adpter;
    DatabaseReference ratingTab;
    FirebaseDatabase databaseF;
    DatabaseReference ratinguser, allrating;
    SqliteFavoraite sqdb;
    private static Float ratinggetShared;

    private static float rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        sqdb = new SqliteFavoraite(this);
        dataIntent = getIntent().getParcelableExtra("Detail");
        databaseF = FirebaseDatabase.getInstance();

        ratinguser = databaseF.getReference("RatingUser");
        allrating = databaseF.getReference("Rating");

        //database
        database = AppDatabase.getDatabaseInstance(this);
        adpter = new RoomAdpter(new ArrayList<Catogray>(), DetailActivity.this);

        if (dataIntent != null) {
            binding.nameDetailes.setText(dataIntent.getName());
            binding.priceDetailes.setText("AED : " + dataIntent.getPrice());
            binding.descDetail.setText(dataIntent.getDesc());
            Glide.with(this).load(dataIntent.getImage()).into(binding.imageDetail);
        }


        binding.addCart.setOnClickListener(this);
        FacoraiteDatabas();
    }

    private void FacoraiteDatabas() {

        //add Fav
        if (sqdb.isFavorit(dataIntent.getName(), Common.currentUser.getPhone()))
            binding.imagfav.setImageResource(R.drawable.ic_favorite_black_24dp);

        //Click fav image
        binding.imagfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Favoraite favoraite = new Favoraite();

                favoraite.setFavname(dataIntent.getName());
                favoraite.setFavdesc(dataIntent.getDesc());
                favoraite.setFacimage(dataIntent.getImage());
                favoraite.setFavprice(dataIntent.getPrice());

                favoraite.setUserphone(Common.currentUser.getPhone());

                if (!sqdb.isFavorit(dataIntent.getName(), Common.currentUser.getPhone())) {

                    sqdb.AddFavorit(favoraite);
                    binding.imagfav.setImageResource(R.drawable.ic_favorite_black_24dp);
                    Toast.makeText(DetailActivity.this, "تم اضافتها للمفضلة", Toast.LENGTH_SHORT).show();

                } else {
                    sqdb.removeFavorit(dataIntent.getName(), Common.currentUser.getPhone());
                    binding.imagfav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    Toast.makeText(DetailActivity.this, "تم حذفها من المفضلة", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_cart:

                database.userDao().insertUser(new Catogray(binding.numberButton.getNumber()
                        , dataIntent.getName()
                        , dataIntent.getImage()
                        , dataIntent.getSize()
                        , dataIntent.getColore()
                        , dataIntent.getDesc()
                        , dataIntent.getPrice()
                        , dataIntent.getDate()
                ));
                Toast.makeText(this, "تم إضافتها الي السلة ", Toast.LENGTH_SHORT).show();

                break;

        }

    }


}