package com.example.hanaiabeauty.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.model.Catogray;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoomAdpter extends RecyclerView.Adapter<RoomAdpter.MyViewHolder> {

    ArrayList<Catogray> list;
    Context mcontext;
    private Callback mCallback;

    public ArrayList<Catogray> getList() {
        return list;
    }

    public void setList(ArrayList<Catogray> list) {
        this.list = list;
    }

    public Callback getmCallback() {
        return mCallback;
    }

    public void setmCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    AppDatabase database = AppDatabase.getDatabaseInstance(mcontext);

    public RoomAdpter(ArrayList<Catogray> names, Context mcontext) {
        this.list = names;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        MyViewHolder chatViewHolder = new MyViewHolder(view);
        return chatViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Catogray mUser = list.get(position);
        TextDrawable textDrawable = TextDrawable.builder()
                .buildRound("" + list.get(position).getNumber(), Color.RED);
        holder.cart_imag.setImageDrawable(textDrawable);
        Locale locale = new Locale("en", "AE");
        NumberFormat ftm = NumberFormat.getCurrencyInstance(locale);
        Double price = (Double.parseDouble(list.get(position).getPrice())) * (Integer.parseInt(list.get(position).getNumber()));
        holder.txtcart_price.setText(ftm.format(price));
        holder.txtcart_name.setText(list.get(position).getName());


        holder.imagedelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallback.onDeleteClick(mUser);
                holder.onBind(position);

            }
        });
    }

   /*     holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext, DetillesActivity.class);
                intent.putExtra("detilles",list.get(position));
                mcontext.startActivity(intent);
            }
        });
*/


    public interface Callback {
        void onDeleteClick(Catogray mUser);
    }

    public void addItems(List<Catogray> userList) {
            list = (ArrayList<Catogray>) userList;
            notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }    }


    public class MyViewHolder extends BaseViewHolder {

        ImageView cart_imag,imagedelet;
        TextView txtcart_price,txtcart_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcart_name=itemView.findViewById(R.id.cart_item_name);
            txtcart_price=itemView.findViewById(R.id.cart_item_price);
            cart_imag=itemView.findViewById(R.id.card_item_count);
            imagedelet=itemView.findViewById(R.id.card_item_delet);

        }

        @Override
        protected void clear() {
            txtcart_name.setText("");

        }


    }
}

