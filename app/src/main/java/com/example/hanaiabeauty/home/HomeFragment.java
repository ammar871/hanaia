package com.example.hanaiabeauty.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.databinding.FragmentHomeBinding;
import com.example.hanaiabeauty.model.Catogray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    private DatabaseReference clothesMen;
    private DatabaseReference clothesfamel;
    private DatabaseReference clothesChild;
    private DatabaseReference footsMan;
    private DatabaseReference footsFemale;
    private DatabaseReference footsChildren;
    private DatabaseReference Exwcwarat;

    private FirebaseDatabase database;
    HomeAdpter adpter;
    ArrayList<Catogray> listClothMen, listClothFame,listClothchold;
    ArrayList<Catogray> listfootMen, listfootFame, listfootchold;
    ArrayList<Catogray> listXcecW;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = binding.getRoot();
        listClothchold = new ArrayList<>();
        listClothFame = new ArrayList<>();
        listClothMen = new ArrayList<>();

        listfootchold = new ArrayList<>();
        listfootFame = new ArrayList<>();
        listfootMen = new ArrayList<>();
        listXcecW = new ArrayList<>();


        //firebase
        database = FirebaseDatabase.getInstance();


        Exwcwarat = database.getReference().child("اكسسوارات");

        //Foots
        footsMan = database.getReference().child("أحذية رجالي");
        footsFemale = database.getReference().child("أحذية حريمي");
        footsChildren = database.getReference().child("أحذيةأطفالي");
        //Clothes
        clothesMen = database.getReference().child("ملابس رجالي");
        clothesfamel = database.getReference().child("ملابس حريمي");
        clothesChild = database.getReference().child("ملابس أطفالي");
        RecyclerInsilaize();
      /*  //Swipe
        binding.swipHome.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark
        );*/

   /*     binding.swipHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //load Data
                loadDatabaseChild();
                loadDatabaseClothesMen();
                loadDatabaseFamel();
                //load Data
                loadDatabaseChildFood();
                loadDatabaseClothesMenFOOD();
                loadDatabaseFamelfood();
                loadDatabaseexces();




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
                //load Data
                loadDatabaseChildFood();
                loadDatabaseClothesMenFOOD();
                loadDatabaseFamelfood();
                loadDatabaseexces();

            }
        });
*/




        //load Data
        loadDatabaseChild();
        loadDatabaseClothesMen();
        loadDatabaseFamel();
        //load Data
        loadDatabaseChildFood();
        loadDatabaseClothesMenFOOD();
        loadDatabaseFamelfood();
        loadDatabaseexces();

        return view;
    }



    private void RecyclerInsilaize() {
        binding.RcClothMen.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        binding.RcClothchild.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        binding.RcClothfamel.setLayoutManager(new LinearLayoutManager(getActivity()
                , RecyclerView.HORIZONTAL, false));
        binding.RcFootschild.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        binding.RcFootsfamel.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        binding.RcFootsMen.setLayoutManager(new LinearLayoutManager(getActivity()
                , RecyclerView.HORIZONTAL, false));
        binding.RcExecWaratchild.setLayoutManager(new  LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));



        binding.RcExecWaratchild.setHasFixedSize(true);

        binding.RcFootsfamel.setHasFixedSize(true);
        binding.RcFootsMen.setHasFixedSize(true);
        binding.RcFootschild.setHasFixedSize(true);

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

    private void loadDatabaseexces() {


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
