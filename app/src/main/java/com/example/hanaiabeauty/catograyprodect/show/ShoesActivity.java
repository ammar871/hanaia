package com.example.hanaiabeauty.catograyprodect.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.databinding.ActivityShoesBinding;
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

public class ShoesActivity extends AppCompatActivity {


    private DatabaseReference footsMan;
    private DatabaseReference footsFemale;
    private DatabaseReference footsChildren;
    private FirebaseDatabase database;

    HomeAdpter adpter;
    ArrayList<Catogray> listfootMen, listfootFame, listfootchold;
    ActivityShoesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shoes);
        listfootchold = new ArrayList<>();
        listfootFame = new ArrayList<>();
        listfootMen = new ArrayList<>();

        //firebase
        database = FirebaseDatabase.getInstance();

        //Foots
        footsMan = database.getReference().child("أحذية رجالي");
        footsFemale = database.getReference().child("أحذية حريمي");
        footsChildren = database.getReference().child("أحذيةأطفالي");

        RecyclerInsilaize();
        loadDatabaseChildFood();
        loadDatabaseClothesMenFOOD();
        loadDatabaseFamelfood();
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShoesActivity.this, HomeActivity.class));
                finish();
            }
        });

    }



    private void RecyclerInsilaize() {

        binding.RcFootschild.setLayoutManager(new LinearLayoutManager(ShoesActivity.this, RecyclerView.HORIZONTAL, false));
        binding.RcFootsfamel.setLayoutManager(new LinearLayoutManager(ShoesActivity.this, RecyclerView.HORIZONTAL, false));

        binding.RcFootsMen.setLayoutManager(new LinearLayoutManager(ShoesActivity.this
                , RecyclerView.HORIZONTAL, false));


        binding.RcFootsfamel.setHasFixedSize(true);
        binding.RcFootsMen.setHasFixedSize(true);
        binding.RcFootschild.setHasFixedSize(true);


    }



    private void loadDatabaseChildFood() {


        footsChildren.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listfootchold.add(blog);
                    Collections.reverse(listfootchold);
                }
                adpter = new HomeAdpter(listfootchold, ShoesActivity.this);


                binding.RcFootschild.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void loadDatabaseClothesMenFOOD() {


        footsMan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listfootMen.add(blog);
                    Collections.reverse(listfootMen);
                }
                adpter = new HomeAdpter(listfootMen, ShoesActivity.this);


                binding.RcFootsMen.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void loadDatabaseFamelfood() {


        footsFemale.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listfootFame.add(blog);
                    Collections.reverse(listfootFame);
                }
                adpter = new HomeAdpter(listfootFame, ShoesActivity.this);


                binding.RcFootsfamel.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
