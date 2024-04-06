package com.androidpprog2.baseprojectsallemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidpprog2.baseprojectsallemovies.model.Movie;

public class MainActivity extends AppCompatActivity implements MovieRecyclerView.OnMovieClickListener {
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieRecyclerView = new MovieRecyclerView(this);
        movieRecyclerView.setOnMovieClickListener(this);
        recyclerView.setAdapter(movieRecyclerView);
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
