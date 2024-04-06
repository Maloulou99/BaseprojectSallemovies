package com.androidpprog2.baseprojectsallemovies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidpprog2.baseprojectsallemovies.model.Movie;
import com.androidpprog2.baseprojectsallemovies.utils.Utils;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewGenres;
    private TextView textViewLength;
    private TextView textViewReview;
    private TextView textViewLanguage;
    private TextView textViewExtract;
    private ImageView imageViewThumbnail;
    private TextView textViewCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_movie_detail);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewGenres = findViewById(R.id.textViewGenres);
        textViewLength = findViewById(R.id.textViewLength);
        textViewReview = findViewById(R.id.textViewReview);
        textViewLanguage = findViewById(R.id.textViewLanguage);
        textViewExtract = findViewById(R.id.textViewExtract);
        imageViewThumbnail = findViewById(R.id.imageViewThumbnail);
        textViewCast = findViewById(R.id.textViewCast);

        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        String movieTitle = getIntent().getStringExtra("movie_title");
        Movie movie = Utils.parseMovieFromJson(this, "movies.json", movieTitle);

        if (movie != null) {
            textViewTitle.setText(movie.getTitle());
            textViewYear.setText(String.valueOf(movie.getYear()));
            textViewGenres.setText(String.valueOf(movie.getGenres()));
            textViewLength.setText(String.valueOf(movie.getLengthAsString()));
            textViewReview.setText(String.valueOf(movie.getReview()));
            textViewLanguage.setText(movie.getLanguage());
            textViewExtract.setText(movie.getExtract());
            textViewCast.setText(String.valueOf(movie.getCast()));
            Picasso.get().load(movie.getThumbnail()).into(imageViewThumbnail);
        }
    }
}