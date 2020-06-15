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
import com.example.hanaiabeauty.databinding.ActivityPeatyBinding;
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

public class PeatyActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference Ref;
    ActivityPeatyBinding binding;
    AdpterHorsintal adpter;
    ArrayList<Catogray> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_peaty);
        list = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        Ref =database.getReference().child("مستحضرات التجميل");
        binding.list.setLayoutManager(new GridLayoutManager(PeatyActivity.this, 2));
        binding.list.setHasFixedSize(true);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PeatyActivity.this, HomeActivity.class));
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
                adpter = new AdpterHorsintal(list, PeatyActivity.this);


                binding.list.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
