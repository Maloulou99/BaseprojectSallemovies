package com.androidpprog2.baseprojectsallemovies;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.baseprojectsallemovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<MovieRecyclerView.ViewHolder> {

    private List<Movie> movieList;

    public MovieRecyclerView(Context context) {
        movieList = loadMovieData(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.imageViewThumbnail.setImageURI(Uri.parse(movie.getThumbnail()));
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewDuration.setText(String.valueOf(movie.getLength()));
        holder.textViewGenre.setText(TextUtils.join(" ", movie.getGenres()));
        holder.textViewReview.setText(String.valueOf(movie.getReview()));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewThumbnail;
        TextView textViewTitle;
        TextView textViewReview;
        TextView textViewGenres;
        TextView textViewGenre;
        TextView textViewDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            textViewGenres = itemView.findViewById(R.id.textViewGenres);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
        }
    }

    // Method to load movie data from JSON file
    private List<Movie> loadMovieData(Context context) {
        List<Movie> movieList = new ArrayList<>();
        try {
            // Read JSON file from assets
            InputStream is = context.getAssets().open("movies.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            // Parse JSON data
            JSONArray jsonArray = new JSONArray(json);

            // Iterate through each movie in the JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Extract movie data
                String title = jsonObject.getString("title");
                JSONArray genresArray = jsonObject.getJSONArray("genres");
                List<String> genres = new ArrayList<>();
                for (int j = 0; j < genresArray.length(); j++) {
                    genres.add(genresArray.getString(j));
                }
                int length = jsonObject.getInt("length");
                double review = jsonObject.getDouble("review");
                String thumbnail = jsonObject.getString("thumbnail"); // Assuming thumbnail is stored as resource ID

                // Create Movie object and add it to the list
                movieList.add(new Movie(title, genres, review, length, thumbnail));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    // Method to provide the adapter instance
    public RecyclerView.Adapter<MovieRecyclerView.ViewHolder> getAdapter() {
        return this;
    }
}