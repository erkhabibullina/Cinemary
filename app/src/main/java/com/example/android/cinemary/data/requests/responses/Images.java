package com.example.android.cinemary.data.requests.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Images {

    @SerializedName("id")
    private int tmdbId;
    private List<Backdrop> backdrops;
    private List<Poster> posters;

    public int getTmdbId() {
        return tmdbId;
    }

    public List<Backdrop> getBackdrops() {
        return new ArrayList<>(backdrops);
    }

    public List<Poster> getPosters() {
        return new ArrayList<>(posters);
    }

    public class Backdrop {

        @SerializedName("aspect_ratio")
        private double aspectRatio;
        @SerializedName("file_path")
        private String filePath;
        private int height;
        @SerializedName("iso_639_1")
        private String iso;
        @SerializedName("vote_average")
        private double voteAverage;
        @SerializedName("vote_count")
        private int voteCount;
        private int width;

        public double getAspectRatio() {
            return aspectRatio;
        }

        public String getFilePath() {
            return filePath;
        }

        public int getHeight() {
            return height;
        }

        public String getIso() {
            return iso;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public int getWidth() {
            return width;
        }
    }

    public class Poster {

        @SerializedName("aspect_ratio")
        private double aspectRatio;
        @SerializedName("file_path")
        private String filePath;
        private int height;
        @SerializedName("iso_639_1")
        private String iso;
        @SerializedName("vote_average")
        private double voteAverage;
        @SerializedName("vote_count")
        private int voteCount;
        private int width;

        public double getAspectRatio() {
            return aspectRatio;
        }

        public String getFilePath() {
            return filePath;
        }

        public int getHeight() {
            return height;
        }

        public String getIso() {
            return iso;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public int getWidth() {
            return width;
        }
    }
}
