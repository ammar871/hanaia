package com.example.hanaiabeauty.sign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.databinding.ActivitySignUpBinding;
import com.example.hanaiabeauty.model.UserDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Sign_Up_Activity extends AppCompatActivity {
    private DatabaseReference tabuser;
    private FirebaseDatabase mDatabase;
    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign__up_);

        //firebase
        mDatabase = FirebaseDatabase.getInstance();
        tabuser = mDatabase.getReference().child("UserDetail");
        binding.btnsinguLOgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_Up_Activity.this, LoginActivity.class));
                finish();
            }
        });

        binding.btnsingupu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Common.isNetworkOnline(getApplicationContext())) {
                    if (isvalue()) {

                        userSignup();

                    } else {
                        Toast.makeText(Sign_Up_Activity.this, "من فضلك أكمل البيانات ", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(Sign_Up_Activity.this, "من فضلك اتصل بالانترنت ", Toast.LENGTH_SHORT).show();

                    return;
                }

            }
        });

    }

    private void userSignup() {
        final ProgressDialog pd = new ProgressDialog(Sign_Up_Activity.this);
        pd.setMessage("loading");
        pd.show();

        tabuser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(binding.editPhoneNumber.getEditText().getText().toString()).exists()) {

                    pd.dismiss();
                    Alrtlogout(binding.editPhoneNumber.getEditText().getText().toString());

                } else {

                    pd.dismiss();
                    UserDetail user = new UserDetail(binding.editnameu.getEditText().getText().toString(),
                            binding.editpass.getEditText().getText().toString()
                            , binding.editPhoneNumber.getEditText().getText().toString()
                            , binding.editserce.getEditText().getText().toString());
                    tabuser.child(binding.editPhoneNumber.getEditText().getText().toString()).setValue(user);
                    Common.currentUser = user;
                    Toast.makeText(Sign_Up_Activity.this, "تم التسجيل بنجاح ", Toast.LENGTH_SHORT).show();
//                            Intent home=new Intent(SingUp.this,Home.class);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    boolean isvalue() {
        if (binding.editPhoneNumber.getEditText().getText().toString().isEmpty()) {

            binding.editPhoneNumber.getEditText().setError("من فضلك أدخل رقم الهاتف ");

            return false;
        } else if (binding.editnameu.getEditText().getText().toString().isEmpty()) {

            binding.editnameu.getEditText().setError("من فضلك أدخل اسمك");
            return false;

        } else if (binding.editpass.getEditText().getText().toString().isEmpty()) {

            binding.editpass.getEditText().setError("من فضلك أدخل الرقم السري ");

            return false;

        } else if (binding.editserce.getEditText().getText().toString().isEmpty()) {

            binding.editserce.getEditText().setError("من فضلك أدخل الرقم مجدداً");

            return false;

        } else if (!binding.editpass.getEditText().getText().toString().equalsIgnoreCase
                (binding.editserce.getEditText().getText().toString())){

            binding.editserce.getEditText().setError("الرقم السرى غير متطابق  ");
            return false;
        }
        else {
            return true;
        }


    }
    private void Alrtlogout(final String phone) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("تغيير كلمة المرور ");
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_logout, null);
        alert.setView(dialogView);
        alert.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        alert.setPositiveButton("تغيير ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {


                Query applesQuery = tabuser.child(phone);
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                            Toast.makeText(Sign_Up_Activity.this, "تم تغيير البيانات ", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
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

}
