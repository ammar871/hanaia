package com.example.hanaiabeauty.cart;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.databinding.FragmentCartBinding;
import com.example.hanaiabeauty.model.Catogray;
import com.example.hanaiabeauty.model.Request;
import com.example.hanaiabeauty.model.UserDetail;
import com.example.hanaiabeauty.notifecatoin.ApiServes;
import com.example.hanaiabeauty.notifecatoin.Claint;
import com.example.hanaiabeauty.notifecatoin.Data;
import com.example.hanaiabeauty.notifecatoin.MyResponse;
import com.example.hanaiabeauty.notifecatoin.Sender;
import com.example.hanaiabeauty.notifecatoin.Token;
import com.example.hanaiabeauty.roomdatabase.AppDatabase;
import com.example.hanaiabeauty.roomdatabase.RoomAdpter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment implements RoomAdpter.Callback {
    private AppDatabase database;
    private RoomAdpter mUserAdapter;
    private LinearLayoutManager mLayoutManager;
    FragmentCartBinding binding;
    TextView txttotalpric;
    Button btnplace;
    FirebaseDatabase databasef;
    DatabaseReference requestd;
    UserDetail user = new UserDetail();
    ArrayList<Catogray> cart = new ArrayList<>();
    ApiServes mservies;

    private static final String API_KEY = "AIzaSyCN_L4SkyPyphSKQB2tlHY8oMYO38HU5mw";
    private static final String TAG = "main";
    PlacesClient placesClient;
    private final int AUTOCOMPLETE_REQUEST_CODE = 001;
    Place adresse;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        View view = binding.getRoot();


        database = AppDatabase.getDatabaseInstance(getActivity());


        databasef = FirebaseDatabase.getInstance();
        requestd = databasef.getReference("Requests");
        binding.btnplaceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cart.size() > 0) {
                    Alert_dialog();
                    //  setUp();

                } else {

                    Toast.makeText(getContext(), "السلة فارغة ", Toast.LENGTH_SHORT).show();

                }
            }
        });

        setUp();

        return view;
    }

    private void setUp() {
        cart = (ArrayList<Catogray>) database.userDao().getAll();
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerCart.setLayoutManager(mLayoutManager);
        binding.recyclerCart.setItemAnimator(new DefaultItemAnimator());
        mUserAdapter = new RoomAdpter(cart, getActivity());
        mUserAdapter.notifyDataSetChanged();
        mUserAdapter.setmCallback(this);
        binding.recyclerCart.setAdapter(mUserAdapter);


        //total

        Double total = 0.0;
        for (Catogray orders : cart) {


            total += (Double.parseDouble(orders.getPrice())) * (Integer.parseInt(orders.getNumber()));
            Locale locale = new Locale("en", "AE");
            NumberFormat ftm = NumberFormat.getCurrencyInstance(locale);
            binding.total.setText(ftm.format(total));


        }
    }

    @Override
    public void onDeleteClick(Catogray mUser) {
        database.userDao().delete(mUser);
        mUserAdapter.addItems(database.userDao().getAll());

        setUp();
        if (cart.size() == 0) {
            binding.total.setText("AED 00,000");
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        mUserAdapter.addItems(database.userDao().getAll());
        setUp();

    }

    @Override
    public void onResume() {
        super.onResume();
        mUserAdapter.addItems(database.userDao().getAll());
        setUp();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }

    private void Alert_dialog() {

        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("ارسال طلب ");
        alert.setMessage("برجاء أدخل عنوان صحيح");
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.order_address_comment, null);


        final EditText edtaddresse = dialogView.findViewById(R.id.ednaddresse_dialog);

        final EditText edtcomment = dialogView.findViewById(R.id.comment);
        alert.setView(dialogView);
        alert.setIcon(R.drawable.ic_cart);
        alert.setPositiveButton( "ارسال", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request request = new Request(
                        Common.currentUser.getPhone(),
                        Common.currentUser.getName(),
                        edtaddresse.getText().toString(),
                        binding.total.getText().toString(),
                        "0",
                        edtcomment.getText().toString(),
                        //      String.format("%s,$s",adresse.getLatLng().latitude,adresse.getLatLng().longitude),
                        cart
                );

                String order_number = String.valueOf(System.currentTimeMillis());
                requestd.child(order_number)
                        .setValue(request);
                database.clearAllTables();
                //  database.notify();
                setUp();
                binding.total.setText("AED 00,000");
                sendNotifaction(order_number);
                SuccsusFragment fragment = new SuccsusFragment();
                fragment.show(getFragmentManager(), null);

                dialog.dismiss();
            }
        });
        alert.setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void sendNotifaction(final String name) {
        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference().child("Tokens");
        Query data = tokens.orderByChild("isservecToken").equalTo(true);
        // Log.v("dataall",data.toString());
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot1 : dataSnapshot.getChildren()) {

                    Token servertoken = postSnapshot1.getValue(Token.class);
                    Data notifacation = new Data("SS", name);
                    Sender sender = new Sender(servertoken.getToken(), notifacation);
                    Log.v("respos", postSnapshot1.getValue().toString());
                    mservies = Claint.getClient().create(ApiServes.class);
                    mservies.sendNotifectoin(sender)
                            .enqueue(new Callback<MyResponse>() {
                                @Override
                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                    // Toast.makeText(Cart.this, ""+response.body().success, Toast.LENGTH_SHORT).show();
                                    if (response.code() == 200) {
                                        if (response.body().success == 1) {

                                            Toast.makeText(getActivity(), "شكرا لك ", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MyResponse> call, Throwable t) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }

}
