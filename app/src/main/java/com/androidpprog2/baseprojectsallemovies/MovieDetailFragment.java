package com.androidpprog2.baseprojectsallemovies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidpprog2.baseprojectsallemovies.R;
import com.androidpprog2.baseprojectsallemovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MovieDetailFragment extends Fragment {

    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewGenres;
    private TextView textViewLength;
    private TextView textViewReview;
    private TextView textViewLanguage;
    private TextView textViewExtract;
    private ImageView imageViewThumbnail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewYear = view.findViewById(R.id.textViewYear);
        textViewGenres = view.findViewById(R.id.textViewGenres);
        textViewLength = view.findViewById(R.id.textViewLength);
        textViewReview = view.findViewById(R.id.textViewReview);
        textViewLanguage = view.findViewById(R.id.textViewLanguage);
        textViewExtract = view.findViewById(R.id.textViewExtract);
        imageViewThumbnail = view.findViewById(R.id.imageViewThumbnail);

        // Set movie data
        Movie movie = getMovieData();
        if (movie != null) {
            textViewTitle.setText(movie.getTitle());
            textViewYear.setText(String.valueOf(movie.getYear()));
            textViewGenres.setText(listToString(movie.getGenres()));
            textViewLength.setText(String.valueOf(movie.getLength()));
            textViewReview.setText(String.valueOf(movie.getReview()));
            textViewLanguage.setText(movie.getLanguage());
            textViewExtract.setText(movie.getExtract());
            imageViewThumbnail = view.findViewById(R.id.imageViewThumbnail);

        }
    }

    // Method to convert list to string
    private String listToString(java.util.List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            sb.append(item).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    // Method to read JSON file and parse movie data
    private Movie getMovieData() {
        try {
            // Read JSON file from assets
            InputStream is = requireActivity().getAssets().open("movies.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            // Parse JSON data
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0); // Assuming only one movie for now

            // Extract movie data
            String title = jsonObject.getString("title");
            int year = jsonObject.getInt("year");
            JSONArray castArray = jsonObject.getJSONArray("cast");
            java.util.List<String> cast = new java.util.ArrayList<>();
            for (int i = 0; i < castArray.length(); i++) {
                cast.add(castArray.getString(i));
            }
            JSONArray genresArray = jsonObject.getJSONArray("genres");
            java.util.List<String> genres = new java.util.ArrayList<>();
            for (int i = 0; i < genresArray.length(); i++) {
                genres.add(genresArray.getString(i));
            }
            int length = jsonObject.getInt("length");
            double review = jsonObject.getDouble("review");
            String language = jsonObject.getString("language");
            String extract = jsonObject.getString("extract");
            String thumbnail = jsonObject.getString("thumbnail");

            // Create and return Movie object
            return new Movie(title, year, cast, genres, length, review, language, extract, thumbnail);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}