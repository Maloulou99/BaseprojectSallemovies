package com.androidpprog2.baseprojectsallemovies.utils;

import android.content.Context;

import com.androidpprog2.baseprojectsallemovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static Movie parseMovieFromJson(Context context, String fileName, String movieTitle) {
        Movie movie = null;
        try {
            String json = loadJsonFromAsset(context, fileName);
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                if (title.equals(movieTitle)) {
                    int year = jsonObject.getInt("year");
                    JSONArray castArray = jsonObject.getJSONArray("cast");
                    List<String> cast = new ArrayList<>();
                    for (int j = 0; j < castArray.length(); j++) {
                        cast.add(castArray.getString(j));
                    }
                    JSONArray genresArray = jsonObject.getJSONArray("genres");
                    List<String> genres = new ArrayList<>();
                    for (int j = 0; j < genresArray.length(); j++) {
                        genres.add(genresArray.getString(j));
                    }
                    int length = jsonObject.getInt("length");
                    double review = jsonObject.getDouble("review");
                    String language = jsonObject.getString("language");
                    String extract = jsonObject.getString("extract");
                    String thumbnail = jsonObject.getString("thumbnail");
                    movie = new Movie(title, year, cast, genres, length, review, language, extract, thumbnail);
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }

    private static String loadJsonFromAsset(Context context, String fileName) {
        String json;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

