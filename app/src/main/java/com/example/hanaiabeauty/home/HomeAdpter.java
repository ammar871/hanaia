package com.example.hanaiabeauty.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.database.SqliteFavoraite;
import com.example.hanaiabeauty.detailes.DetailActivity;
import com.example.hanaiabeauty.detailes.DetailsClothesActivity;
import com.example.hanaiabeauty.model.Catogray;
import com.example.hanaiabeauty.model.Favoraite;


import java.util.ArrayList;

public class HomeAdpter extends RecyclerView.Adapter<HomeAdpter.HomeViewHolder> {

    ArrayList<Catogray> list;
    Context mcontext;
    SqliteFavoraite database;
    HomeAdpter Instans;

    public HomeAdpter(ArrayList<Catogray> names, Context mcontext) {
        this.list = names;
        this.mcontext = mcontext;
        database = new SqliteFavoraite(mcontext);

    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        HomeViewHolder chatViewHolder = new HomeViewHolder(view);
        return chatViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {

        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        Glide.with(mcontext).load(list.get(position).getImage()).into(holder.imageView);

        holder.txtDate.setText(list.get(position).getDate().toString());

        //add Fav
        if (database.isFavorit(list.get(position).getName(), Common.currentUser.getPhone()))
            holder.imgfav.setImageResource(R.drawable.ic_favorite_black_24dp);

        //Click fav image
        holder.imgfav.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Favoraite favoraite = new Favoraite();

                                                 favoraite.setFavname(list.get(position).getName());
                                                 favoraite.setFavdesc(list.get(position).getDesc());
                                                 favoraite.setFacimage(list.get(position).getImage());
                                                 favoraite.setSize(list.get(position).getSize());
                                                 favoraite.setColore(list.get(position).getColore());
                                                 favoraite.setFavprice(list.get(position).getPrice());


                                                 favoraite.setUserphone(Common.currentUser.getPhone());

                                                 if (!database.isFavorit(list.get(position).getName(), Common.currentUser.getPhone())) {

                                                     database.AddFavorit(favoraite);
                                                     holder.imgfav.setImageResource(R.drawable.ic_favorite_black_24dp);
                                                     Toast.makeText(mcontext, "تم اضافتها للمفضلة", Toast.LENGTH_SHORT).show();

                                                 } else {
                                                     database.removeFavorit(list.get(position).getName(), Common.currentUser.getPhone());
                                                     holder.imgfav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                                                     Toast.makeText(mcontext, "تم حذفها من المفضلة", Toast.LENGTH_SHORT).show();
                                                 }
                                             }
                                         }
        );


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, DetailsClothesActivity.class);
                intent.putExtra("Detail", list.get(position));
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView, imgfav;
        TextView name, price, txtDate;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            name = itemView.findViewById(R.id.txtItemName);
            imgfav = itemView.findViewById(R.id.imagfav);
            price = itemView.findViewById(R.id.txtPrice);
            txtDate = itemView.findViewById(R.id.txtItemdate);
        }


    }
}

