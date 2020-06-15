package com.example.hanaiabeauty.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.catograyprodect.CatograyFragment;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.databinding.ActivityHomeBinding;
import com.example.hanaiabeauty.cart.CartFragment;
import com.example.hanaiabeauty.fragments.FavoraiteFragment;
import com.example.hanaiabeauty.fragments.SittingFragment;
import com.example.hanaiabeauty.fragments.SearshFragment;
import com.example.hanaiabeauty.notifecatoin.Token;
import com.example.hanaiabeauty.peger.ClotthesFragment;
import com.example.hanaiabeauty.peger.HomePeger;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class HomeActivity extends AppCompatActivity
        implements
        BottomNavigationView.OnNavigationItemSelectedListener
{

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        loadFragment(new CatograyFragment());

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this);

        Update(FirebaseInstanceId.getInstance().getToken()) ;

    }

    private void Update(String token) {
try {
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference ref= db.getReference("Tokens");
    Token data=new Token(token,false);
    ref.child(Common.currentUser.getPhone()).setValue(data);

} catch (Exception e) {
    e.printStackTrace();
}

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.homeActivity:
                fragment = new CatograyFragment();

                break;

            case R.id.cart:

                fragment = new CartFragment();

                break;
            case R.id.search:

                fragment = new SearshFragment();


                break;

            case R.id.add:

                fragment = new SittingFragment();


                break;
            case R.id.fav:

                fragment = new FavoraiteFragment();



                break;
            default:

        }
        return loadFragment(fragment);

    }



    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.connter_latout, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
