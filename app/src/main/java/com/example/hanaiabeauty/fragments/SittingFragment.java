package com.example.hanaiabeauty.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hanaiabeauty.BuildConfig;
import com.example.hanaiabeauty.R;

import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.databinding.FragmentSittingBinding;
import com.example.hanaiabeauty.detailes.AboutActivity;
import com.example.hanaiabeauty.model.Catogray;
import com.example.hanaiabeauty.sign.LoginActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class SittingFragment extends Fragment implements View.OnClickListener{

    FragmentSittingBinding binding;

    private DatabaseReference mekamlistRf;
    private DatabaseReference perfamrf;
    private DatabaseReference peatylist;
    Common common;
    Uri saveuri;
    FirebaseStorage storage;
    StorageReference storageReference;
    RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    Catogray newCtago;

    public SittingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sitting, container, false);
        View view = binding.getRoot();

        // firebase
        database = FirebaseDatabase.getInstance();
        mekamlistRf = database.getReference().child("مكياج");
        perfamrf = database.getReference().child("العطور");
        peatylist = database.getReference().child("موادتجميل");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        //onclick
        binding.logOutCard.setOnClickListener(this);
        binding.AboutCard.setOnClickListener(this);
        binding.ShareCard.setOnClickListener(this);





        return view;
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){

    case R.id.log_out_card:
        Paper.book().destroy();
        Intent signin = new Intent(getActivity(), LoginActivity.class);
        signin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signin);
        break;
    case R.id.Share_card:

        try {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareMessage= "\nتطبيق  \n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(sharingIntent, "تطبيق بيع العقارات"));
        } catch(Exception e) {

            Toast.makeText(getActivity(), "فشلت العملية", Toast.LENGTH_SHORT).show();
        }
        break;
    case R.id.About_card:

        Intent about = new Intent(getActivity(), AboutActivity.class);
        about.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(about);
        break;
}




    }
 /*    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.numbers, R.layout.coloer_spinner);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        binding.spinner.setAdapter(adapter);

        binding.imageselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImage();
            }
        });

        binding.btnufinshed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newCtago != null) {
                    if (binding.spinner.getSelectedItem().toString().equalsIgnoreCase("اختـار النــوع")) {

                        Toast.makeText(getActivity(), "من فضلك اختار النوع ", Toast.LENGTH_SHORT).show();

                    } else if (binding.spinner.getSelectedItem().toString().equalsIgnoreCase("عطـور")) {

                        perfamrf.push().setValue(newCtago);
                        Toast.makeText(getActivity(), "تمت الاضافة في العطور", Toast.LENGTH_SHORT).show();

                        binding.imageselect.setImageURI(null);
                        binding.edname.setText("");
                        binding.edprice.setText("");
                        binding.eddescribtion.setText("");
                    } else if (binding.spinner.getSelectedItem().toString().equalsIgnoreCase("مــواد تجميل")) {

                        peatylist.push().setValue(newCtago);
                        binding.imageselect.setImageURI(null);
                        Toast.makeText(getActivity(), "تمت الاضافة في موادتجميل", Toast.LENGTH_SHORT).show();
                        binding.edname.setText("");
                        binding.edprice.setText("");
                        binding.eddescribtion.setText("");
                    } else if (binding.spinner.getSelectedItem().toString().equalsIgnoreCase("مكيــاج")) {

                        mekamlistRf.push().setValue(newCtago);
                        Toast.makeText(getActivity(), "تمت الاضافة في المكياج", Toast.LENGTH_SHORT).show();

                        binding.imageselect.setImageURI(null);
                        binding.edname.setText("");
                        binding.edprice.setText("");
                        binding.eddescribtion.setText("");

                    }

                }
            }
        });
        binding.btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UploadImage();


            }
        });
*/
  /*  private void ChooseImage() {
        Intent glary = new Intent(Intent.ACTION_GET_CONTENT);
        glary.setType("image/*");
        glary.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(glary, "Selecet pictur"), Common.PICK_IMAGE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.PICK_IMAGE && resultCode == RESULT_OK && data
                != null && data.getData() != null) {
            saveuri = data.getData();
            binding.imageselect.setImageURI(saveuri);
        }
    }

    private void UploadImage() {
        if (saveuri != null) {
            final ProgressDialog pd = new ProgressDialog(getActivity());
            pd.setMessage("Upload...");
            pd.show();
            String ImageName = UUID.randomUUID().toString();

            final StorageReference imagefolder = storageReference.child("images/" + ImageName);
            imagefolder.putFile(saveuri).
                    addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(getContext(), "upload...", Toast.LENGTH_SHORT).show();
                            imagefolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    newCtago = new Catogray();
                                    newCtago.setName(binding.edname.getText().toString());
                                    newCtago.setDesc(binding.eddescribtion.getText().toString());
                                    newCtago.setPrice(binding.edprice.getText().toString());
                                    newCtago.setImage(uri.toString());

                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    pd.setMessage("upload" + progress + "");
                }
            });
        }
    }*/
}
