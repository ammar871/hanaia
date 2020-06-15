package com.reload.coursat.peger;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.reload.coursat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePeger extends Fragment {
    private ViewPager mviewpager;
    private TabLayout mTablayout;

    SelctionpegerAdapter adapter;

View view;
    public HomePeger() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home_peger, container, false);

        mTablayout =view.findViewById(R.id.main_tabs);
        mviewpager =view.findViewById(R.id.tabvieger);
        //view pager
        mviewpager =view.findViewById(R.id.tabvieger);
        adapter = new SelctionpegerAdapter(getActivity().getSupportFragmentManager());
        mviewpager.setAdapter(adapter);
        mTablayout = view.findViewById(R.id.main_tabs);
        mTablayout.setupWithViewPager(mviewpager);
        return view;
    }

}
