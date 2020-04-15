package com.example.android.cinemary.data.requests.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Credits {

    @SerializedName("id")
    private int tmdbId;
    private List<Cast> cast;
    private List<Crew> crew;

    public int getTmdbId() {
        return tmdbId;
    }

    public List<Cast> getCast() {
        return new ArrayList<>(cast);
    }

    public List<Crew> getCrew() {
        return new ArrayList<>(crew);
    }

    public class Cast {

        @SerializedName("cast_id")
        private int castId;
        private String character;
        @SerializedName("credit_id")
        private String creditId;
        private int gender;
        private String name;
        private int order;
        @SerializedName("profile_path")
        private String profilePath;

        public int getCastId() {
            return castId;
        }

        public String getCharacter() {
            return character;
        }

        public String getCreditId() {
            return creditId;
        }

        public int getGender() {
            return gender;
        }

        public String getName() {
            return name;
        }

        public int getOrder() {
            return order;
        }

        public String getProfilePath() {
            return profilePath;
        }
    }

    public class Crew {

        @SerializedName("credit_id")
        private String creditId;
        private String department;
        private int gender;
        private int id;
        private String job;
        private String name;
        @SerializedName("profile_path")
        private String profilePath;

        public String getCreditId() {
            return creditId;
        }

        public String getDepartment() {
            return department;
        }

        public int getGender() {
            return gender;
        }

        public int getId() {
            return id;
        }

        public String getJob() {
            return job;
        }

        public String getName() {
            return name;
        }

        public String getProfilePath() {
            return profilePath;
        }
    }
}
