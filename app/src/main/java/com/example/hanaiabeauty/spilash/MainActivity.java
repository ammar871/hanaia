package com.example.hanaiabeauty.spilash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.home.HomeActivity;
import com.example.hanaiabeauty.model.UserDetail;
import com.example.hanaiabeauty.sign.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference tabuser;

    private FirebaseDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//paper
        Paper.init(this);
//Check is login
        String user= Paper.book().read(Common.USERKEY);
        String pass=Paper.book().read(Common.USER_PASS);

//        if(user !=null && pass != null){
//
//                Intent startIntent = new Intent(MainActivity.this, HomeActivity.class);
//                startActivity(startIntent);
//            }else {
//            Intent startIntent = new Intent(MainActivity.this, LoginRetroActivity.class);
//            startActivity(startIntent);
//        }

        if(user !=null && pass != null){
            if (!user.isEmpty()&&!pass.isEmpty()){
                login(user,pass);


            }
        }
        else {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        }

    private void login(final String phone, final String pass) {

        if (Common.isNetworkOnline(getApplicationContext())) {
            mDatabase = FirebaseDatabase.getInstance();
            tabuser = mDatabase.getReference().child("UserDetail");

            final ProgressDialog pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("جاري التحميل ....");
            pd.show();

            tabuser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    if (dataSnapshot.child(phone).exists()) {
                        pd.dismiss();
                        UserDetail user = dataSnapshot.child(phone).getValue(UserDetail.class);
                        user.setPhone(phone);
                        if (user.getPasword().equals(pass)) {
                            Intent home = new Intent(MainActivity.this, HomeActivity.class);
                            Common.currentUser = user;
                            startActivity(home);
                            finish();
                        } else {

                            Toast.makeText(MainActivity.this, "فشل الدحول ", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "لايوجد حساب لك محفوظ ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else {

            Toast.makeText(MainActivity.this, "please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}




