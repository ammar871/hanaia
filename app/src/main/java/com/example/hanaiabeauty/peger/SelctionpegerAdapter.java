package com.example.hanaiabeauty.peger;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class SelctionpegerAdapter extends FragmentPagerAdapter {
int id;

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                ClotthesFragment regeustFragment=new ClotthesFragment();
                return regeustFragment;
            case 1 :
                FootsFragment chatFragment=new FootsFragment();
                return chatFragment;

            case 2:
                XecewarFragment friendsFragment=new XecewarFragment();
                return friendsFragment;

        }
        return null;
    }

    public SelctionpegerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        getItem(1);

    }

    @Override
    public int getCount() {
        return 3;
    }


    public CharSequence getPageTitle(int pistion){
    switch (pistion){
        case 0:
            return "الملابس ";
        case 1:
            return "الأحذية ";
        case 2:
            return "الاكسسوارات";

            default:
                return null;

    }

    }
}
