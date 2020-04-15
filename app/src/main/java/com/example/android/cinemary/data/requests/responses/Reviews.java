package com.example.android.cinemary.data.requests.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Reviews {

    @SerializedName("id")
    private int tmdbId;
    private int page;
    private List<Results> results;

    public int getTmdbId() {
        return tmdbId;
    }

    public int getPage() {
        return page;
    }

    public List<Results> getResults() {
        return new ArrayList<>(results);
    }

    public class Results {

        private String author;
        @SerializedName("content")
        private String review;
        private String id;
        @SerializedName("url")
        private String webUrl;

        public String getAuthor() {
            return author;
        }

        public String getReview() {
            return review;
        }

        public String getId() {
            return id;
        }

        public String getWebUrl() {
            return webUrl;
        }
    }
}
