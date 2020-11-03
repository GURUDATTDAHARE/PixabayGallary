package com.gurudattdahare.photos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gurudattdahare.photos.Modal.Guru;

import java.util.ArrayList;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder>{

    ArrayList<Guru> list;
    Context context;
    //constructor
    public Adapter(ArrayList<Guru> list, Context context_)
    {
        this.list = list;
        this.context = context_;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView  likes;
        private TextView download;

        public ViewHolder(View v)
        {
            super(v);
            image =(ImageView)v.findViewById(R.id.guru);
            likes=v.findViewById(R.id.likes);
            download=v.findViewById(R.id.download);
        }

        public ImageView getImage(){ return this.image;}
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(1080,800));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        Glide.with(this.context)
                .load(list.get(position).getURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());
        holder.download.setText(String.valueOf(list.get(position).getDownloads()));
        holder.likes.setText(String.valueOf(list.get(position).getLikes()));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

}

