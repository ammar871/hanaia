package com.example.hanaiabeauty.fragments;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.database.SqliteFavoraite;
import com.example.hanaiabeauty.databinding.FragmentFavoraiteBinding;


public class FavoraiteFragment extends Fragment {

FragmentFavoraiteBinding binding;
AdpteFavoraite adpter;
    public FavoraiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favoraite, container, false);
        View view = binding.getRoot();

        //recycler

        binding.recyclerFzv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerFzv.setHasFixedSize(true);

            adpter=new AdpteFavoraite(new SqliteFavoraite(getActivity()).getAllFavoraite(Common.currentUser.getPhone()),getActivity());
            binding.recyclerFzv.setAdapter(adpter);


        return view;
    }

}
