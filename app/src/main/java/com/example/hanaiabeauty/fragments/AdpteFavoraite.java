package com.example.hanaiabeauty.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.database.SqliteFavoraite;
import com.example.hanaiabeauty.detailes.DetailActivity;
import com.example.hanaiabeauty.detailes.DetailFavActivity;
import com.example.hanaiabeauty.home.HomeAdpter;
import com.example.hanaiabeauty.model.Catogray;
import com.example.hanaiabeauty.model.Favoraite;

import java.util.ArrayList;
import java.util.List;

public class AdpteFavoraite extends RecyclerView.Adapter<AdpteFavoraite.HomeViewHolder> {

    List<Favoraite> list;
    Context mcontext;
    SqliteFavoraite database;

    public AdpteFavoraite(List<Favoraite> names, Context mcontext) {
        this.list = names;
        this.mcontext = mcontext;
        database = new SqliteFavoraite(mcontext);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favoraite, parent, false);
        HomeViewHolder chatViewHolder = new HomeViewHolder(view);
        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getFavname());
        holder.price.setText(list.get(position).getFavprice());
        Glide.with(mcontext).load(list.get(position).getFacimage()).into(holder.imageView);

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.removeFavorit(list.get(position).getFavname(), Common.currentUser.getPhone());
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());
                Toast.makeText(mcontext, "تم حذفها من المفضلة", Toast.LENGTH_SHORT).show();
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, DetailFavActivity.class);
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

        ImageView imageView, imageDelete;
        TextView name, price;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            name = itemView.findViewById(R.id.txtItemName);
            imageDelete = itemView.findViewById(R.id.imagdelete);
            price = itemView.findViewById(R.id.txtPrice);
        }


    }
}

