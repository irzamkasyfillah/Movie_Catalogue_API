package com.example.android.mybotnav;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListTVShowAdapter extends RecyclerView.Adapter<ListTVShowAdapter.ListViewHolder> {
    private ArrayList<TVShow> listTVShow;

    ListTVShowAdapter(ArrayList<TVShow> tvShows) {
        this.listTVShow = tvShows;
    }

    public void refill(ArrayList<TVShow> items) {
        this.listTVShow = new ArrayList<>();
        this.listTVShow.addAll(items);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_tv_show, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        TVShow tvShow = listTVShow.get(position);

        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + tvShow.getPhoto1()).into(holder.imgPhoto);

        holder.tvName.setText(tvShow.getName());
        holder.tvYear.setText(tvShow.getDate().substring(0, 4));
        holder.tvRating.setText(String.valueOf(tvShow.getRating()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), listTVShow.get(position).getName().toUpperCase(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(holder.itemView.getContext(), LoadingTVShowDetail.class);
//                intent.putExtra("name", listTVShow.get(position).getName());
//                intent.putExtra("id", listTVShow.get(position).getId());
//                intent.putExtra("desc", listTVShow.get(position).getDescription());
//                intent.putExtra("date", listTVShow.get(position).getDate());
//                intent.putExtra("photo2", listTVShow.get(position).getPhoto2());

                TVShow tvShow1 = new TVShow();
                tvShow1.setName(listTVShow.get(position).getName());
                tvShow1.setId(listTVShow.get(position).getId());
                tvShow1.setDescription(listTVShow.get(position).getDescription());
                tvShow1.setDate(listTVShow.get(position).getDate());
                tvShow1.setPhoto2(listTVShow.get(position).getPhoto2());
                intent.putExtra(LoadingTVShowDetail.EXTRA_TV_SHOW, tvShow1);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTVShow.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvRating, tvYear;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvName = itemView.findViewById(R.id.name);
            tvRating = itemView.findViewById(R.id.rating);
            tvYear = itemView.findViewById(R.id.year);
        }
    }
}
