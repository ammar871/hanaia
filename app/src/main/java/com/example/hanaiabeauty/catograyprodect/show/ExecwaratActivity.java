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
import com.example.hanaiabeauty.database.SqliteFavoraite;
import com.example.hanaiabeauty.databinding.ActivityExecwaratBinding;
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

public class ExecwaratActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference excewarat;
    ActivityExecwaratBinding binding;
    AdpterHorsintal adpter;
    ArrayList<Catogray> listexecwarat;
SqliteFavoraite databaseSqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_execwarat);

        databaseSqlite=new SqliteFavoraite(this);

        listexecwarat = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        excewarat = database.getReference().child("اكسسوارات");
        binding.list.setLayoutManager(new GridLayoutManager(ExecwaratActivity.this, 2));
        binding.list.setHasFixedSize(true);
        loadExcwarate();
binding.back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(ExecwaratActivity.this, HomeActivity.class));
        finish();
    }
});

    }

    private void loadExcwarate() {


        excewarat.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listexecwarat.add(blog);
                    Collections.reverse(listexecwarat);
                }
                adpter = new AdpterHorsintal(listexecwarat, ExecwaratActivity.this);


                binding.list.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
