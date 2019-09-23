package com.example.android.mybotnav;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "string_extra";
    public String name, description, date, photo2, genre, tagline, production;
    private ImageView imgPhoto;
    private TextView tvName, tvDescription, tvGenre, tvDate, tvTagline, tvProduction;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvName = findViewById(R.id.detail_name);
        tvDescription = findViewById(R.id.detail_description);
        tvGenre = findViewById(R.id.detail_genre);
        tvDate = findViewById(R.id.detail_date);
        imgPhoto = findViewById(R.id.detail_photo);
        tvTagline = findViewById(R.id.detail_tagline);
        tvProduction = findViewById(R.id.detail_production_comp);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        name = movie.getName();
        description = movie.getDescription();
        date = movie.getDate();
        photo2 = movie.getPhoto2();
        genre = movie.getAllgenre();
        tagline = movie.getTagline();
        production = movie.getAllproduction();

        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500" + photo2).into(imgPhoto);

        tvName.setText(name);
        tvDescription.setText(description);
        tvDate.setText(date);
        tvProduction.setText(production);
        tvGenre.setText(genre);
        if (!tagline.isEmpty()) {
            tvTagline.setText("\"" + tagline + "\"");
        } else {
            tvTagline.setHeight(0);
        }
    }
}
