package com.example.homeserviceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homeserviceapp.R;
import com.example.homeserviceapp.datamodels.catmodel;

import java.util.List;

public class catadapter extends RecyclerView.Adapter<catadapter.ViewHolder> {


    private Context context;
    private List<catmodel> list;

    public catadapter(Context context, List<catmodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.catrecyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.catrecimg);
        holder.catrectext.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView catrecimg;
        TextView catrectext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            catrecimg = itemView.findViewById(R.id.catimg);
            catrectext = itemView.findViewById(R.id.cattext);
        }
    }
}
