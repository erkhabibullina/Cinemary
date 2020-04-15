package com.example.android.cinemary.data.requests.responses.tv;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvTopRated {

    @SerializedName("total_pages")
    private int totalPages;
    private List<Results> results;

    public int getTotalPages() {
        return totalPages;
    }

    public List<Results> getResults() {
        return new ArrayList<>(results);
    }

    public class Results {

        @SerializedName("original_name")
        private String originalTitle;
        @SerializedName("genre_ids")
        private List<Integer> genreIds;
        @SerializedName("name")
        private String title;
        private double popularity;
        @SerializedName("origin_country")
        private List<String> originCountry;
        @SerializedName("vote_count")
        private int voteCount;
        @SerializedName("first_air_date")
        private String firstAirDate;
        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("original_language")
        private String originalLanguage;
        @SerializedName("id")
        private int tmdbId;
        @SerializedName("vote_average")
        private double voteAverage;
        @SerializedName("overview")
        private String plot;
        @SerializedName("poster_path")
        private String posterPath;

        public String getOriginalTitle() {
            return originalTitle;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public String getTitle() {
            return title;
        }

        public double getPopularity() {
            return popularity;
        }

        public List<String> getOriginCountry() {
            return originCountry;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public String getFirstAirDate() {
            return firstAirDate;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public int getTmdbId() {
            return tmdbId;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public String getPlot() {
            return plot;
        }

        public String getPosterPath() {
            return posterPath;
        }
    }
}
