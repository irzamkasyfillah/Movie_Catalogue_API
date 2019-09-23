package com.example.android.mybotnav;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailTVShowActivity extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "string_extra";
    public String name, description, date, photo2, genre, status, production;
    private ImageView imgPhoto;
    private TextView tvName, tvDescription, tvGenre, tvDate, tvStatus, tvProduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);

        tvName = findViewById(R.id.detail_name);
        tvDescription = findViewById(R.id.detail_description);
        tvGenre = findViewById(R.id.detail_genre);
        tvDate = findViewById(R.id.detail_date);
        tvStatus = findViewById(R.id.detail_status);
        imgPhoto = findViewById(R.id.detail_photo);
        tvProduction = findViewById(R.id.detail_production_comp);

        TVShow tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

        name = tvShow.getName();
        description = tvShow.getDescription();
        date = tvShow.getDate();
        photo2 = tvShow.getPhoto2();
        genre = tvShow.getAllgenre();
        status = tvShow.getStatus();
        production = tvShow.getAllproduction();

        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500" + photo2).into(imgPhoto);
        tvName.setText(name);
        tvDescription.setText(description);
        tvDate.setText(date);
        tvProduction.setText(production);
        tvGenre.setText(genre);
        tvStatus.setText(status);
    }
}
