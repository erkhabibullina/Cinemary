package com.example.android.cinemary.data.requests.responses.movies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviesPopular {

    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    private List<Results> results;

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Results> getResults() {
        return new ArrayList<>(results);
    }

    public class Results {

        @SerializedName("vote_count")
        private int voteCount;
        @SerializedName("id")
        private int tmdbId;
        private boolean video;
        @SerializedName("vote_average")
        private double voteAverage;
        private String title;
        private double popularity;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("original_language")
        private String originalLanguage;
        @SerializedName("original_title")
        private String originalTitle;
        @SerializedName("genre_ids")
        private List<Integer> genreIds;
        @SerializedName("backdrop_path")
        private String backdropPath;
        private boolean adult;
        @SerializedName("overview")
        private String plot;
        @SerializedName("release_date")
        private String releaseDate;

        public int getVoteCount() {
            return voteCount;
        }

        public int getTmdbId() {
            return tmdbId;
        }

        public boolean isVideo() {
            return video;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public String getTitle() {
            return title;
        }

        public double getPopularity() {
            return popularity;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public List<Integer> getGenreIds() {
            return new ArrayList<>(genreIds);
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public boolean isAdult() {
            return adult;
        }

        public String getPlot() {
            return plot;
        }

        public String getReleaseDate() {
            return releaseDate;
        }
    }
}
