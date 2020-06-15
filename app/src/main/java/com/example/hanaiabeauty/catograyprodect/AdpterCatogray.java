package com.example.hanaiabeauty.catograyprodect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.catograyprodect.show.ExecwaratActivity;
import com.example.hanaiabeauty.catograyprodect.show.MotfarkatActivity;
import com.example.hanaiabeauty.catograyprodect.show.Offers1Activity;
import com.example.hanaiabeauty.catograyprodect.show.Offers2Activity;
import com.example.hanaiabeauty.catograyprodect.show.Offers3Activity;
import com.example.hanaiabeauty.catograyprodect.show.HandActivity;
import com.example.hanaiabeauty.catograyprodect.show.ParfamActivity;
import com.example.hanaiabeauty.catograyprodect.show.PeatyActivity;
import com.example.hanaiabeauty.catograyprodect.show.ShoesActivity;
import com.example.hanaiabeauty.catograyprodect.show.ShowClotheActivity;

import java.util.ArrayList;

public class AdpterCatogray extends RecyclerView.Adapter<AdpterCatogray.HolderAdpter> {

    ArrayList<Model> list;
    Context mcontext;


    public AdpterCatogray(ArrayList<Model> names, Context mcontext) {
        this.list = names;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public HolderAdpter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catogray_item, parent, false);
        HolderAdpter chatViewHolder = new HolderAdpter(view);
        return chatViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdpter holder, final int position) {
        holder.txnamecto.setText(list.get(position).getName());
        holder.imaecato.setImageResource(list.get(position).getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {

                    case 0:
                        Intent intent = new Intent(mcontext, ShowClotheActivity.class);
                        mcontext.startActivity(intent);
                        break;
                    case 1:
                        intentMothed(ShoesActivity.class);
                        break;
                    case 2:
                        intentMothed(PeatyActivity.class);
                        break;
                    case 3:
                        intentMothed(ExecwaratActivity.class);
                        break;
                    case 4:
                        intentMothed(ParfamActivity.class);
                        break;
                    case 5:
                        intentMothed(MotfarkatActivity.class);
                        break;
                    case 6:
                        intentMothed(HandActivity.class);
                        break;
                    case 7:
                        intentMothed(Offers1Activity.class);
                        break;
                    case 8:
                        intentMothed(Offers2Activity.class);
                        break;
                    case 9:
                        intentMothed(Offers3Activity.class);
                        break;


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderAdpter extends RecyclerView.ViewHolder {
        public TextView txnamecto;
        public ImageView imaecato;

        public HolderAdpter(@NonNull View itemView) {
            super(itemView);
            txnamecto = itemView.findViewById(R.id.txtItemName);
            imaecato = itemView.findViewById(R.id.imag_cto);

        }


    }

    private void intentMothed(Class a) {

        Intent intent = new Intent(mcontext, a);

        mcontext.startActivity(intent);
    }

}

