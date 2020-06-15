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
import com.example.hanaiabeauty.databinding.ActivityShowClotheBinding;
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

public class ShowClotheActivity extends AppCompatActivity {
ActivityShowClotheBinding binding;

    private DatabaseReference clothesMen;
    private DatabaseReference clothesfamel;
    private DatabaseReference clothesChild;
    ArrayList<Catogray> listClothMen, listClothFame,listClothchold;
    private FirebaseDatabase database;

    HomeAdpter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_clothe);
        listClothchold = new ArrayList<>();
        listClothFame = new ArrayList<>();
        listClothMen = new ArrayList<>();
        //firebase
        database = FirebaseDatabase.getInstance();
        //Clothes
        clothesMen = database.getReference().child("ملابس رجالي");
        clothesfamel = database.getReference().child("ملابس حريمي");
        clothesChild = database.getReference().child("ملابس أطفالي");

        //
        RecyclerInsilaize();
        loadDatabaseChild();
        loadDatabaseClothesMen();
        loadDatabaseFamel();
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowClotheActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    private void RecyclerInsilaize() {
        binding.RcClothMen.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.RcClothchild.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.RcClothfamel.setLayoutManager(new LinearLayoutManager(this
                , RecyclerView.HORIZONTAL, false));
        binding.RcClothchild.setHasFixedSize(true);
        binding.RcClothfamel.setHasFixedSize(true);
        binding.RcClothMen.setHasFixedSize(true);
    }

    private void loadDatabaseChild() {


        clothesChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listClothchold.add(blog);
                    Collections.reverse(listClothchold);
                }
                adpter = new HomeAdpter(listClothchold, ShowClotheActivity.this);


                binding.RcClothchild.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void loadDatabaseClothesMen() {


        clothesMen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listClothMen.add(blog);
                    Collections.reverse(listClothMen);

                }
                adpter = new HomeAdpter(listClothMen, ShowClotheActivity.this);


                binding.RcClothMen.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void loadDatabaseFamel() {


        clothesfamel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listClothFame.add(blog);
                    Collections.reverse(listClothFame);
                }
                adpter = new HomeAdpter(listClothFame, ShowClotheActivity.this);


                binding.RcClothfamel.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
