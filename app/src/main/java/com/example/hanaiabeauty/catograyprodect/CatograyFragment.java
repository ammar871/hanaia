package com.example.hanaiabeauty.catograyprodect;


import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.databinding.FragmentCatograyBinding;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CatograyFragment extends Fragment {

    FragmentCatograyBinding binding;
    ArrayList<Model> list = new ArrayList<>();
    AdpterCatogray adpter;

    public CatograyFragment() {

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_catogray, container, false);
        View view = binding.getRoot();
        loadData();


        return view;
    }

    private void loadData() {


        binding.recyclerCatogray.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recyclerCatogray.setHasFixedSize(true);
        list = new ArrayList<>();
        list.add(new Model("الملابـس", R.drawable.clothes, ""));
        list.add(new Model("الأحذيـة", R.drawable.foots, ""));
        list.add(new Model("مستحضرات التجميل ", R.drawable.peaty, ""));
        list.add(new Model("الاكسسوارات", R.drawable.execwar, ""));
        list.add(new Model("العطــور", R.drawable.parfam, ""));
        list.add(new Model("متفرقات", R.drawable.motfar, ""));
        list.add(new Model("شنط يد نسائية", R.drawable.hand, ""));
        list.add(new Model("عروض وخصومات 1", R.drawable.offer, ""));
        list.add(new Model("عروض وخصومات 2", R.drawable.offer, ""));
        list.add(new Model("عروض وخصومات 3", R.drawable.offer, ""));

        list.add(new Model("تـاجر1", R.drawable.storone, ""));
        list.add(new Model("تـاجر2", R.drawable.stortwo, ""));
        list.add(new Model("تـاجر3", R.drawable.storthre, ""));
        list.add(new Model("تـاجر4", R.drawable.stortfour, ""));
        adpter = new AdpterCatogray(list, getActivity());
        binding.recyclerCatogray.setAdapter(adpter);
    }

}
