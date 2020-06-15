package com.reload.coursat.peger;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.reload.coursat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClotthesFragments extends Fragment {


    public ClotthesFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clotthes_fragments, container, false);
    }

}
