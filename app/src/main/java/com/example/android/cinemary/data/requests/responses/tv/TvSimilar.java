package com.example.android.cinemary.data.requests.responses.tv;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvSimilar {

    private int page;
    private List<Results> results;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;

    public int getPage() {
        return page;
    }

    public List<Results> getResults() {
        return new ArrayList<>(results);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public class Results {

        @SerializedName("original_name")
        private String originalTitle;
        private int id;
        @SerializedName("name")
        private String title;
        @SerializedName("vote_count")
        private int voteCount;
        @SerializedName("vote_average")
        private double voteAverage;
        @SerializedName("first_air_date")
        private String firstAirDate;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("genre_ids")
        private List<Integer> genreIds;
        @SerializedName("original_language")
        private String originalLanguage;
        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("overview")
        private String plot;
        @SerializedName("origin_country")
        private List<String> originCountry;
        private double popularity;

        public String getOriginalTitle() {
            return originalTitle;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public String getFirstAirDate() {
            return firstAirDate;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public List<Integer> getGenreIds() {
            return new ArrayList<>(genreIds);
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public String getPlot() {
            return plot;
        }

        public List<String> getOriginCountry() {
            return new ArrayList<>(originCountry);
        }

        public double getPopularity() {
            return popularity;
        }
    }
}
