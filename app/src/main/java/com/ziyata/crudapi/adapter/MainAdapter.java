package com.ziyata.crudapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ziyata.crudapi.DetailActivity;
import com.ziyata.crudapi.MainActivity;
import com.ziyata.crudapi.R;
import com.ziyata.crudapi.model.UserData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// TODO 8
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final Context context;
    private final List<UserData> userDataList;


    public MainAdapter(Context context, List<UserData> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Mengisi data ke dalam model userData dari list
        final UserData userData = userDataList.get(i);

        // Menampilkan data ke layar
        viewHolder.txtFirst.setText(userData.getFirst_name());
        viewHolder.txtLast.setText(userData.getLast_name());

        // Setting option
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);

        // Menampilkan gambar menggunakan ke dalam glide dan juga memasukkan optionnya
        Glide.with(context).load(userData.getAvatar()).apply(options).into(viewHolder.imgAvatar);

        // Membuat onClick pada item RecycleView
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat object Bundle untuk menampung data
                Bundle bundle = new Bundle();

                // Mengisi bundle dengan data id user
                bundle.putInt(DetailActivity.KEY_ID, userData.getId());

                // Melakukan perpindahan halaman dengan membawa data
                context.startActivity(new Intent(context, DetailActivity.class).putExtras(bundle));

            }
        });

    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgAvatar)
        ImageView imgAvatar;
        @BindView(R.id.txtFirst)
        TextView txtFirst;
        @BindView(R.id.txtLast)
        TextView txtLast;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
