package com.example.hanaiabeauty.sign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.databinding.ActivityLoginBinding;
import com.example.hanaiabeauty.home.HomeActivity;
import com.example.hanaiabeauty.model.UserDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private DatabaseReference tabuser;

    private FirebaseDatabase mDatabase;
ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //firebase
        mDatabase = FirebaseDatabase.getInstance();
        tabuser = mDatabase.getReference().child("UserDetail");
//paper
        Paper.init(this);


        binding.btnSignlogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(LoginActivity.this,Sign_Up_Activity.class);
        startActivity(intent);
    }
});
        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Common.isNetworkOnline(getApplicationContext())) {
                    if (binding.cbRemmber.isChecked()) {
                        Paper.book().write(Common.USERKEY, binding.editePhone.getEditText().getText().toString());
                        Paper.book().write(Common.USER_PASS, binding.editePasss.getEditText().getText().toString());
                    }
                   if (oncheck()) {
                       Load_User();
                   }else {
                       Toast.makeText(LoginActivity.this, "من فضلك أكمل البيانات ", Toast.LENGTH_SHORT).show();
                   }

                }
                 //    Paper.book().write(Common.USERKEY, editphon.getEditText().getText().toString());
                     //   Paper.book().write(Common.USER_PASS, editPassword.getEditText().getText().toString());


              else {

                    Toast.makeText(LoginActivity.this, "من فضلك اتصل بالانترنت ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    private void Load_User() {
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("loading");
        pd.show();


        tabuser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(binding.editePhone.getEditText().getText().toString()).exists()) {
                    pd.dismiss();
                    UserDetail user = dataSnapshot.child(binding.editePhone.getEditText().getText().toString()).getValue(UserDetail.class);
                    user.setPhone(binding.editePhone.getEditText().getText().toString());
                    if (user.getPasword().equals(binding.editePasss.getEditText().getText().toString())) {
                        Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                        Common.currentUser = user;
                        startActivity(home);
                        finish();
                    } else {

                        Toast.makeText(LoginActivity.this, "رقم غير صحيح ", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    pd.dismiss();
                    Toast.makeText(LoginActivity.this, "الرقم غير موجود", Toast.LENGTH_SHORT).show();
                    /*startActivity(new Intent(LoginRetroActivity.this, Sign_Up_Activity.class));
                    finish();*/
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean oncheck() {

        if ((binding.editePhone.getEditText().getText().toString().isEmpty())) {
           binding.editePhone.getEditText().setError("من فضلك ادحل رقم الهاتف ");
            return false;
        } else if ((binding.editePasss.getEditText().getText().toString().isEmpty())) {

            binding.editePasss.getEditText().setError("من فضلك ادحل الرقم السرى");
            return false;

        } else {

            return true;

        }
    }}
