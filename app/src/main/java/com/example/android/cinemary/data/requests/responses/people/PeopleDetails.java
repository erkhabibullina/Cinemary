package com.example.android.cinemary.data.requests.responses.people;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PeopleDetails {

    private String birthday;
    @SerializedName("known_for_department")
    private String knownForDepartment;
    private String deathday;
    @SerializedName("id")
    private int tmdbId;
    private String name;
    @SerializedName("also_known_as")
    private List<String> alsoKnownAs;
    private int gender;
    private String biography;
    private double popularity;
    @SerializedName("place_of_birth")
    private String placeOfBirth;
    @SerializedName("profile_path")
    private String profilePath;
    private boolean adult;
    @SerializedName("imdb_id")
    private String imdbId;
    private String homepage;

    public String getBirthday() {
        return birthday;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getDeathday() {
        return deathday;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public String getName() {
        return name;
    }

    public List<String> getAlsoKnownAs() {
        return new ArrayList<>(alsoKnownAs);
    }

    public int getGender() {
        return gender;
    }

    public String getBiography() {
        return biography;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getHomepage() {
        return homepage;
    }
}
