package com.example.hanaiabeauty.peger;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.databinding.FragmentHomePegerBinding;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePeger extends Fragment {


    SelctionpegerAdapter adapter;
FragmentHomePegerBinding binding;

    public HomePeger() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_peger, container, false);
        View view = binding.getRoot();

       binding.textClothes.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


           }
       });


        //view pager


       // adapter = new SelctionpegerAdapter(getActivity().getSupportFragmentManager());
      /*  mviewpager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mviewpager);*/
        return view;
    }
/*
    @Override
    public void onStart() {
        super.onStart();
        adapter = new SelctionpegerAdapter(getActivity().getSupportFragmentManager());
        mviewpager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mviewpager);
    }*/

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
           getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.connter_latout, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
