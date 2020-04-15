package com.example.android.cinemary.data.requests.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Genres {

    private List<Genre> genres;

    public List<Genre> getGenres() {
        return new ArrayList<>(genres);
    }

    public class Genre {

        @SerializedName("id")
        private int tmdbId;
        private String name;

        public int getTmdbId() {
            return tmdbId;
        }

        public String getName() {
            return name;
        }
    }
}
