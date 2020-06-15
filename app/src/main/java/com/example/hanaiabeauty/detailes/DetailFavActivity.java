package com.example.hanaiabeauty.detailes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.database.SqliteFavoraite;
import com.example.hanaiabeauty.databinding.ActivityDetailBinding;
import com.example.hanaiabeauty.databinding.ActivityDetailFavBinding;
import com.example.hanaiabeauty.model.Catogray;
import com.example.hanaiabeauty.model.Favoraite;
import com.example.hanaiabeauty.roomdatabase.AppDatabase;
import com.example.hanaiabeauty.roomdatabase.RoomAdpter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DetailFavActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityDetailFavBinding binding;
    Favoraite dataIntent;
    AppDatabase database;
    RoomAdpter adpter;
    SqliteFavoraite sqdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_fav);

        sqdb = new SqliteFavoraite(this);
        dataIntent = getIntent().getParcelableExtra("Detail");


        binding.addCartfav.setOnClickListener(this);

        //database
        database = AppDatabase.getDatabaseInstance(this);
        adpter = new RoomAdpter(new ArrayList<Catogray>(), DetailFavActivity.this);

        if (dataIntent != null) {
            binding.nameDetailes.setText(dataIntent.getFavname());
            binding.priceDetailes.setText("OMR : " + dataIntent.getFavprice());
            binding.descDetail.setText(dataIntent.getFavdesc());
            Glide.with(this).load(dataIntent.getFacimage()).into(binding.imageDetail);
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_cartfav:

                database.userDao().insertUser(new Catogray(binding.numberButton.getNumber()
                        , dataIntent.getFavname()
                        , dataIntent.getFacimage()
                        , dataIntent.getFavdesc()
                        , dataIntent.getFavprice()));
                Toast.makeText(this, "تم إضافتها الي السلة ", Toast.LENGTH_SHORT).show();

                break;

        }

    }
}

