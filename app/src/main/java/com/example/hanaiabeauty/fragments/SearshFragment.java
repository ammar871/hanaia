package com.example.hanaiabeauty.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.databinding.FragmentSearshBinding;
import com.example.hanaiabeauty.home.HomeAdpter;
import com.example.hanaiabeauty.model.Catogray;
import com.example.hanaiabeauty.model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearshFragment extends Fragment {

    FragmentSearshBinding binding;


    private DatabaseReference clothesMen;
    private DatabaseReference clothesfamel;
    private DatabaseReference clothesChild;
    private DatabaseReference footsMan;
    private DatabaseReference footsFemale;
    private DatabaseReference footsChildren;
    private DatabaseReference Exwcwarat;
    private DatabaseReference parfam;
    private DatabaseReference peaty;
    private DatabaseReference offers1;
    private DatabaseReference offers2;
    private DatabaseReference offers3;
    private DatabaseReference handfame;
    private DatabaseReference motferkat;

    ArrayList<Catogray> listClothMen, listClothFame, listClothchold;
    ArrayList<Catogray> listfootMen, listfootFame, listfootchold;
    ArrayList<Catogray> listXcecW, offer1listn, offer2list, offer3list, offers4list, motfarkatlist, peatylist, parfamlist;

    FirebaseStorage storage;
    StorageReference storageReference;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    List<String> suggestlist = new ArrayList<>();

    HomeAdpter adpter;
    ArrayList<Catogray> list;


    public SearshFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_searsh, container, false);
        View view = binding.getRoot();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.numbers, R.layout.coloer_spinner);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        binding.spinner.setAdapter(adapter);
        listClothchold = new ArrayList<>();
        listClothFame = new ArrayList<>();
        listClothMen = new ArrayList<>();

        listfootchold = new ArrayList<>();
        listfootFame = new ArrayList<>();
        listfootMen = new ArrayList<>();
        listXcecW = new ArrayList<>();

        offer1listn = new ArrayList<>();
        offer2list = new ArrayList<>();
        offer3list = new ArrayList<>();
        offers4list = new ArrayList<>();
        motfarkatlist = new ArrayList<>();
        parfamlist = new ArrayList<>();
        peatylist = new ArrayList<>();
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
        parfam = database.getReference().child("عطور");
        peaty = database.getReference().child("مستحضرات التجميل");
        offers1 = database.getReference().child("عروض 1");
        offers2 = database.getReference().child("عروض 2");
        offers3 = database.getReference().child("عروض 3");
        handfame = database.getReference().child("شنط يد نسائية");
        motferkat = database.getReference().child("متفرقات");

        //recyecler
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recyclerView.setHasFixedSize(true);

       loadAllsuggect();


        binding.searchBar.setLastSuggestions(suggestlist);
        binding.searchBar.setCardViewElevation(10);
        binding.searchBar.setHint("أدخل اسم المنتج ");

        binding.searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                List<String> suggest = new ArrayList<String>();
                for (String search : suggestlist) {
                    if (search.toLowerCase().contains(binding.searchBar.getText().toLowerCase()))

                        suggest.add(search);


                }

                binding.searchBar.setLastSuggestions(suggest);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled)
                    binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

                if (binding.spinner.getSelectedItem().toString().equalsIgnoreCase("اختـار النــوع")) {

                    Toast.makeText(getActivity(), "من فضلك اختار النوع ", Toast.LENGTH_SHORT).show();

                } else if (binding.spinner.getSelectedItem().toString()
                        .equalsIgnoreCase("ملابس نساء")) {
                    loadDatabasclothFame(text);
                    loadSuggest(clothesfamel);
                    //   sendNotifaction("نوع جديد");
                } else if (binding.spinner.getSelectedItem().toString()
                        .equalsIgnoreCase("ملابس أطفال")) {

                    loadDatabasclothChild(text);
                    loadSuggest(clothesChild);
                    // sendNotifaction("نوع جديد");

                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("ملابس رجال")) {

                    loadDatabaseClothmen(text);
                    loadSuggest(clothesMen);
                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("أحذية رجال")) {
                    loadDatabasfootmen(text);
                    loadSuggest(footsMan);
                    //  sendNotifaction("نوع جديد");

                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("أحذية نساء")) {

                    loadDatabasfoofame(text);
                    loadSuggest(footsFemale);
                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("أحذيةأطفال")) {
                    loadDatabasefootChild(text);
                    loadSuggest(footsChildren);

                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("اكسسوارات")) {
                    loadDatabaseExcec(text);
                    loadSuggest(Exwcwarat);
                }  // ==========================================
                else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("مستحضرات تجميل")) {

                    loadDatabasepeaty(text);
                    loadSuggest(peaty);
                    // sendNotifaction("نوع جديد");

                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("العطـور")) {

                    loadDatabaseparfam(text);

                    loadSuggest(parfam);
                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("عروض 1")) {

                    loadDatabaseofeer1(text);

                    loadSuggest(offers1);
                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("عروض 2")) {
                    loadDatabaseoffer2(text);
                    loadSuggest(offers2);

                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("عروض 3")) {

                    loadDatabaseoffer3(text);
                    loadSuggest(offers3);
                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("متفرقات")) {
                    loadDatabasemotafrekat(text);
                    loadSuggest(motferkat);

                } else if (binding.spinner.getSelectedItem().toString().
                        equalsIgnoreCase("شنط يد نسائية")) {
                    loadDatabaseoffers4(text);
                    loadSuggest(handfame);
                }

            }

            @Override
            public void onButtonClicked(int buttonCode) {


            }
        });


        return view;
    }

    private void loadDatabaseoffers4(CharSequence text) {


        Query applesQuery = handfame.orderByChild("name").equalTo(text.toString());

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    offers4list.add(blog);
                }
                adpter = new HomeAdpter(offers4list, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void loadDatabaseClothmen(CharSequence text) {


        Query applesQuery = clothesMen.orderByChild("name").equalTo(text.toString());

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listClothMen.add(blog);
                }
                adpter = new HomeAdpter(listClothMen, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabasclothFame(CharSequence text) {

        Query applesQuery = clothesfamel.orderByChild("name").equalTo(text.toString());

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "name: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listClothFame.add(blog);
                }
                adpter = new HomeAdpter(listClothFame, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabasclothChild(CharSequence text) {


        Query applesQuery = clothesChild.orderByChild("name").equalTo(text.toString());

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listClothchold.add(blog);
                }
                adpter = new HomeAdpter(listClothchold, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabasfootmen(CharSequence text) {


        Query applesQuery = footsMan.orderByChild("name").equalTo(text.toString());

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listfootMen.add(blog);
                }
                adpter = new HomeAdpter(listfootMen, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabasfoofame(CharSequence text) {


        Query applesQuery = footsFemale.orderByChild("name").equalTo(text.toString());

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listfootFame.add(blog);
                }
                adpter = new HomeAdpter(listfootFame, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabasefootChild(CharSequence text) {
        Query applesQuery = footsChildren.orderByChild("name").equalTo(text.toString());

        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listfootchold.add(blog);
                }
                adpter = new HomeAdpter(listfootchold, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabaseExcec(CharSequence text) {
        Query applesQuery = Exwcwarat.orderByChild("name").equalTo(text.toString());

        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    listXcecW.add(blog);
                }
                adpter = new HomeAdpter(listXcecW, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadSuggest(DatabaseReference ref) {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnabshot : dataSnapshot.getChildren()) {

                    Food item = postsnabshot.getValue(Food.class);
                    suggestlist.add(item.getName());
                }
                binding.searchBar.setLastSuggestions(suggestlist);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabaseofeer1(CharSequence text) {
        Query applesQuery = offers1.orderByChild("name").equalTo(text.toString());

        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    offer1listn.add(blog);
                }
                adpter = new HomeAdpter(offer1listn, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabaseoffer2(CharSequence text) {
        Query applesQuery = offers2.orderByChild("name").equalTo(text.toString());

        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    offer2list.add(blog);
                }
                adpter = new HomeAdpter(offer2list, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabaseoffer3(CharSequence text) {
        Query applesQuery = offers3.orderByChild("name").equalTo(text.toString());

        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    offer3list.add(blog);
                }
                adpter = new HomeAdpter(offer3list, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabasemotafrekat(CharSequence text) {
        Query applesQuery = motferkat.orderByChild("name").equalTo(text.toString());

        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    motfarkatlist.add(blog);
                }
                adpter = new HomeAdpter(motfarkatlist, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabasepeaty(CharSequence text) {
        Query applesQuery = peaty.orderByChild("name").equalTo(text.toString());

        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    peatylist.add(blog);
                }
                adpter = new HomeAdpter(peatylist, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadDatabaseparfam(CharSequence text) {
        Query applesQuery = parfam.orderByChild("name").equalTo(text.toString());

        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("Tagstorag", "onSuccess: " + dataSnapshot.child("name").getValue());
                    Catogray blog = dataSnapshot1.getValue(Catogray.class);


                    parfamlist.add(blog);
                }
                adpter = new HomeAdpter(parfamlist, getActivity());


                binding.recyclerView.setAdapter(adpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadAllsuggect() {
        if (binding.spinner.getSelectedItem().toString().equalsIgnoreCase("اختـار النــوع"))
        {

            Toast.makeText(getActivity(), "من فضلك اختار النوع ", Toast.LENGTH_SHORT).show();

        } else if (binding.spinner.getSelectedItem().toString()
                .equalsIgnoreCase("ملابس نساء")) {

            loadSuggest(clothesfamel);

            //   sendNotifaction("نوع جديد");
        } else if (binding.spinner.getSelectedItem().toString()
                .equalsIgnoreCase("ملابس أطفال")) {


            loadSuggest(clothesChild);
            // sendNotifaction("نوع جديد");

        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("ملابس رجال")) {


            loadSuggest(clothesMen);
        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("أحذية رجال")) {

            loadSuggest(footsMan);
            //  sendNotifaction("نوع جديد");

        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("أحذية نساء")) {


            loadSuggest(footsFemale);
        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("أحذيةأطفال")) {

            loadSuggest(footsChildren);

        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("اكسسوارات")) {

            loadSuggest(Exwcwarat);
        }  // ==========================================
        else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("مستحضرات تجميل")) {


            loadSuggest(peaty);
            // sendNotifaction("نوع جديد");

        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("العطـور")) {


            loadSuggest(parfam);
        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("عروض 1")) {


            loadSuggest(offers1);
        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("عروض 2")) {

            loadSuggest(offers2);

        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("عروض 3")) {


            loadSuggest(offers3);
        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("متفرقات")) {

            loadSuggest(motferkat);

        } else if (binding.spinner.getSelectedItem().toString().
                equalsIgnoreCase("شنط يد نسائية")) {

            loadSuggest(handfame);
        }

    }
}
