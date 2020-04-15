package com.example.android.cinemary.data.requests.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Videos {

    @SerializedName("id")
    private int tmdbId;
    private List<Results> results;

    public int getTmdbId() {
        return tmdbId;
    }

    public List<Results> getResults() {
        return new ArrayList<>(results);
    }

    public class Results {

        private String id;
        @SerializedName("iso_639_1")
        private String iso639;
        @SerializedName("iso_3166_1")
        private String iso3166;
        private String key;
        private String name;
        private String site;
        private int size;
        private String type;

        public String getId() {
            return id;
        }

        public String getIso639() {
            return iso639;
        }

        public String getIso3166() {
            return iso3166;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getSite() {
            return site;
        }

        public int getSize() {
            return size;
        }

        public String getType() {
            return type;
        }
    }
}
