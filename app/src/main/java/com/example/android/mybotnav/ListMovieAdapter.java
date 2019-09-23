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

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {
    private ArrayList<Movie> listMovie;

    ListMovieAdapter(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    public void refill(ArrayList<Movie> items) {
        this.listMovie = new ArrayList<>();
        this.listMovie.addAll(items);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie, viewGroup, false);
        return new ListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Movie movie = listMovie.get(position);

        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + movie.getPhoto1()).into(holder.imgPhoto);
        holder.tvName.setText(movie.getName());
        holder.tvRating.setText(String.valueOf(movie.getRating()));
        holder.tvYear.setText(movie.getDate().substring(0, 4));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), listMovie.get(position).getName().toUpperCase(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(holder.itemView.getContext(), LoadingMovieDetail.class);

                Movie movie1 = new Movie();
                movie1.setId(listMovie.get(position).getId());
                movie1.setName(listMovie.get(position).getName());
                movie1.setPhoto2(listMovie.get(position).getPhoto2());
                movie1.setGenre(listMovie.get(position).getGenre());
                movie1.setDate(listMovie.get(position).getDate());
                movie1.setDescription(listMovie.get(position).getDescription());

                intent.putExtra(LoadingMovieDetail.EXTRA_MOVIE, movie1);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listMovie.size();
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
