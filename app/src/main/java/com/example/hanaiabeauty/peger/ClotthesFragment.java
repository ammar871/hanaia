package com.example.hanaiabeauty.peger;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.databinding.FragmentClotthesBinding;
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
public class ClotthesFragment extends Fragment {
    FragmentClotthesBinding binding;
    private DatabaseReference clothesMen;
    private DatabaseReference clothesfamel;
    private DatabaseReference clothesChild;
    private FirebaseDatabase database;
    HomeAdpter adpter;
    ArrayList<Catogray> listClothMen, listClothFame,listClothchold;
    public ClotthesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_clotthes, container, false);
        View view = binding.getRoot();

        listClothchold = new ArrayList<>();
        listClothFame = new ArrayList<>();
        listClothMen = new ArrayList<>();

        //Swipe
       binding.swipHome.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark
        );

        binding.swipHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //load Data
                loadDatabaseChild();
                loadDatabaseClothesMen();
                loadDatabaseFamel();



            }
        });
//swip post
        binding.swipHome.post(new Runnable() {
            @Override
            public void run() {

                //load Data
                loadDatabaseChild();
                loadDatabaseClothesMen();
                loadDatabaseFamel();

            }
        });


        //firebase
        database = FirebaseDatabase.getInstance();


        //Clothes
        clothesMen = database.getReference().child("ملابس رجالي");
        clothesfamel = database.getReference().child("ملابس حريمي");
        clothesChild = database.getReference().child("ملابس أطفالي");
//Recler
        RecyclerInsilaize();


        //load Data
        loadDatabaseChild();
        loadDatabaseClothesMen();
        loadDatabaseFamel();

        return view;
    }

    private void RecyclerInsilaize() {
        binding.RcClothMen.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        binding.RcClothchild.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        binding.RcClothfamel.setLayoutManager(new LinearLayoutManager(getActivity()
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
                }
                adpter = new HomeAdpter(listClothchold, getActivity());


                binding.RcClothchild.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        binding.swipHome.setRefreshing(false);
    }

    private void loadDatabaseClothesMen() {


        clothesMen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listClothMen.add(blog);
                }
                adpter = new HomeAdpter(listClothMen, getActivity());


                binding.RcClothMen.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        binding.swipHome.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void loadDatabaseFamel() {


        clothesfamel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("image1").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listClothFame.add(blog);
                }
                adpter = new HomeAdpter(listClothFame, getActivity());


                binding.RcClothfamel.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        binding.swipHome.setRefreshing(false);
    }

}
