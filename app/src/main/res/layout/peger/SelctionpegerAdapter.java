package com.reload.coursat.peger;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class SelctionpegerAdapter extends FragmentPagerAdapter {
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                ClotthesFragments regeustFragment=new ClotthesFragments();
                return regeustFragment;
            case 1 :
                FootsFragment chatFragment=new FootsFragment();
                return chatFragment;

            case 2:
                XecewarFragment friendsFragment=new XecewarFragment();
                return friendsFragment;


                default:
                    return null;

        }

    }

    public SelctionpegerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }


    public CharSequence getPageTitle(int pistion){
    switch (pistion){
        case 0:
            return "Clothes";
        case 1:
            return "Foots";
        case 2:
            return "Xecewarat";

            default:
                return null;

    }

    }
}
