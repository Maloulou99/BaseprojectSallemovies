package com.androidpprog2.baseprojectsallemovies;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.baseprojectsallemovies.model.Movie;
import com.squareup.picasso.Picasso;

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
    private Context context;
    private OnMovieClickListener onMovieClickListener;

    public MovieRecyclerView(Context context) {
        this.context = context;
        movieList = loadMovieData(context);
    }

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public void setOnMovieClickListener(OnMovieClickListener listener) {
        this.onMovieClickListener = listener;
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
        Picasso.get().load(movie.getThumbnail()).into(holder.imageViewThumbnail);
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewDuration.setText(String.valueOf(movie.getLengthAsString()));
        holder.textViewGenre.setText(String.valueOf(movie.getGenres()));
        holder.textViewReview.setText(String.valueOf(movie.getReview()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Movie selectedMovie = movieList.get(position);
                    if (onMovieClickListener != null) {
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra("movie_title", selectedMovie.getTitle());
                        context.startActivity(intent);
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewThumbnail;
        TextView textViewTitle;
        TextView textViewReview;
        TextView textViewGenre;
        TextView textViewDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
        }
    }

    private List<Movie> loadMovieData(Context context) {
        List<Movie> movieList = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("movies.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                JSONArray genresArray = jsonObject.getJSONArray("genres");
                List<String> genres = new ArrayList<>();
                for (int j = 0; j < genresArray.length(); j++) {
                    genres.add(genresArray.getString(j));
                }
                int length = jsonObject.getInt("length");
                double review = jsonObject.getDouble("review");
                String thumbnail = jsonObject.getString("thumbnail");
                movieList.add(new Movie(title, genres, review, length, thumbnail));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }

}
