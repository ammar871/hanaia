package com.example.hanaiabeauty.cart;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.databinding.FragmentSuccsusBinding;
import com.example.hanaiabeauty.home.HomeActivity;


public class SuccsusFragment extends AppCompatDialogFragment {

FragmentSuccsusBinding binding;
    public SuccsusFragment() {



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_succsus, container, false);
        View view = binding.getRoot();

binding.btnGoBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }
});
        return view;
    }

}
