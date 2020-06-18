package com.example.hanaiabeauty.catograyprodect.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.catograyprodect.AdpterHorsintal;
import com.example.hanaiabeauty.databinding.ActivityMotfarkatBinding;
import com.example.hanaiabeauty.databinding.ActivityOffers3Binding;
import com.example.hanaiabeauty.home.HomeActivity;
import com.example.hanaiabeauty.home.HomeAdpter;
import com.example.hanaiabeauty.model.Catogray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Offers3Activity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference Ref;
    ActivityOffers3Binding binding;
    AdpterHorsintal adpter;
    ArrayList<Catogray> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_offers3);
        list = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        Ref = database.getReference().child("عروض 3");
        binding.list.setLayoutManager(new GridLayoutManager(Offers3Activity.this, 2));
        binding.list.setHasFixedSize(true);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Offers3Activity.this, HomeActivity.class));
                finish();
            }
        });
        loadData();

    }
    private void loadData() {


        Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    list.add(blog);
                    Collections.reverse(list);
                }
                adpter = new AdpterHorsintal(list, Offers3Activity.this);


                binding.list.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
