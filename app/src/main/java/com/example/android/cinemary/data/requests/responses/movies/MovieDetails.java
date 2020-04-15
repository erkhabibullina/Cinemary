package com.example.android.cinemary.data.requests.responses.movies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails {

    private boolean adult;
    @SerializedName("backdrop_path")
    private String BackdropPath;
    @SerializedName("belongs_to_collection")
    private List<BelongsToCollection> BelongsToCollection;
    private int budget;
    private List<Genre> genres;
    private String homepage;
    @SerializedName("id")
    private int tmdbId;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String plot;
    private double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;
    @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries;
    @SerializedName("release_date")
    private String releaseDate;
    private int revenue;
    private int runtime;
    @SerializedName("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;
    @SerializedName("status")
    private String releaseStatus;
    private String tagline;
    private boolean video;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private int voteCount;

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return BackdropPath;
    }

    public List<BelongsToCollection> getBelongsToCollection() {
        return new ArrayList<>(BelongsToCollection);
    }

    public int getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return new ArrayList<>(genres);
    }

    public String getHomepage() {
        return homepage;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPlot() {
        return plot;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return new ArrayList<>(productionCompanies);
    }

    public List<ProductionCountry> getProductionCountries() {
        return new ArrayList<>(productionCountries);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return new ArrayList<>(spokenLanguages);
    }

    public String getReleaseStatus() {
        return releaseStatus;
    }

    public String getTagline() {
        return tagline;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public class BelongsToCollection {

        private int id;
        private String name;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("backdrop_path")
        private String backdropPath;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public String getBackdropPath() {
            return backdropPath;
        }
    }

    public class Genre {

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public class ProductionCompany {

        private int id;
        @SerializedName("logo_path")
        private String logoPath;
        private String name;
        @SerializedName("origin_country")
        private String originCountry;

        public int getId() {
            return id;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public String getName() {
            return name;
        }

        public String getOriginCountry() {
            return originCountry;
        }
    }

    public class ProductionCountry {

        @SerializedName("iso_3166_1")
        private String iso;
        private String name;

        public String getIso() {
            return iso;
        }

        public String getName() {
            return name;
        }
    }

    public class SpokenLanguage {

        @SerializedName("iso_639_1")
        private String iso;
        private String name;

        public String getIso() {
            return iso;
        }

        public String getName() {
            return name;
        }
    }
}