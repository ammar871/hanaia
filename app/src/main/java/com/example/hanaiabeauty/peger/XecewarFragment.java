package com.example.hanaiabeauty.peger;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.databinding.FragmentXecewarBinding;
import com.example.hanaiabeauty.home.HomeAdpter;
import com.example.hanaiabeauty.model.Catogray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class XecewarFragment extends Fragment {

    private DatabaseReference Exwcwarat;
FragmentXecewarBinding binding;
    HomeAdpter adpter;
    private FirebaseDatabase database;

    ArrayList<Catogray> listXcecW;
    public XecewarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_xecewar, container, false);
        View view = binding.getRoot();

        listXcecW = new ArrayList<>();


        //firebase
        database = FirebaseDatabase.getInstance();
        Exwcwarat = database.getReference().child("اكسسوارات");

        RecyclerInsilaize();
        loadDatabaseChild();

        return view;
    }
    private void RecyclerInsilaize() {
        binding.RcExecWaratchild.setLayoutManager(new GridLayoutManager(getActivity(),2));



        binding.RcExecWaratchild.setHasFixedSize(true);
    }


    private void loadDatabaseChild() {


        Exwcwarat .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listXcecW.add(blog);
                }
                adpter = new HomeAdpter(listXcecW, getActivity());


                binding.RcExecWaratchild.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
