package com.example.hanaiabeauty.peger;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.databinding.FragmentFootsBinding;
import com.example.hanaiabeauty.home.HomeAdpter;
import com.example.hanaiabeauty.model.Catogray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FootsFragment extends Fragment {
    FragmentFootsBinding binding;
    private DatabaseReference footsMan;
    private DatabaseReference footsFemale;
    private DatabaseReference footsChildren;
    private FirebaseDatabase database;
    ArrayList<Catogray> listfootMen, listfootFame, listfootchold;

    HomeAdpter adpter;
    public FootsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_foots, container, false);
        View view = binding.getRoot();


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

        //load Data
        loadDatabaseChildFood();
        loadDatabaseClothesMenFOOD();
        loadDatabaseFamelfood();


        return view;
    }


    private void RecyclerInsilaize() {
        binding.RcFootschild.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        binding.RcFootsfamel.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        binding.RcFootsMen.setLayoutManager(new LinearLayoutManager(getActivity()
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
                }
                adpter = new HomeAdpter(listfootchold, getActivity());


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
                }
                adpter = new HomeAdpter(listfootMen, getActivity());


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
                }
                adpter = new HomeAdpter(listfootFame, getActivity());


                binding.RcFootsfamel.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
