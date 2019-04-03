package com.example.async_task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    List<NewsData> newsData;
    Context context;

    public RecycleAdapter(List<NewsData> newsData, Context context) {
        this.newsData = newsData;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.auth.setText(newsData.get(position).getAuthor());
        holder.newsName.setText(newsData.get(position).getName());
        holder.newsTitle.setText(newsData.get(position).getTitle());
        Picasso.get().load(newsData.get(position).getUrlToImage()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return newsData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView auth,newsTitle,newsName;
        ImageView newsImage;
        public MyViewHolder(View itemView) {
            super(itemView);

            auth=(TextView)itemView.findViewById(R.id.newsauthor);
            newsTitle=(TextView)itemView.findViewById(R.id.newstitle);
            newsName=(TextView)itemView.findViewById(R.id.newsname);
            newsImage=(ImageView)itemView.findViewById(R.id.imgurl);

        }
    }
}
