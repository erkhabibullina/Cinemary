package com.example.android.cinemary.data.requests.responses.tv;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvDetails {

    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("created_by")
    private List<CreatedBy> createdby;
    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime;
    @SerializedName("first_air_date")
    private String firstAirDate;
    private List<Genre> genres;
    private String homepage;
    @SerializedName("id")
    private int tmdbId;
    @SerializedName("in_production")
    private boolean inProduction;
    private List<String> languages;
    @SerializedName("last_air_date")
    private String lastAirDate;
    @SerializedName("last_episode_to_air")
    private LastEpisodeToAir lastEpisodeToAir;
    @SerializedName("name")
    private String title;
    @SerializedName("next_episode_to_air")
    private NextEpisodeToAir nextEpisodeToAir;
    private List<Network> networks;
    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;
    @SerializedName("number_of_seasons")
    private int numberOfSeasons;
    @SerializedName("origin_country")
    private List<String> originCountry;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_name")
    private String originalTitle;
    @SerializedName("overview")
    private String plot;
    private double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    private List<ProductionCompany> productionCompanies;
    private List<Season> seasons;
    private String status;
    private String type;
    @SerializedName("vote_average")
    private int voteAverage;
    @SerializedName("vote_count")
    private int voteCount;

    public String getBackdropPath() {
        return backdropPath;
    }

    public List<CreatedBy> getCreatedby() {
        return new ArrayList<>(createdby);
    }

    public List<Integer> getEpisodeRunTime() {
        return new ArrayList<>(episodeRunTime);
    }

    public String getFirstAirDate() {
        return firstAirDate;
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

    public boolean isInProduction() {
        return inProduction;
    }

    public List<String> getLanguages() {
        return new ArrayList<>(languages);
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public LastEpisodeToAir getLastEpisodeToAir() {
        return lastEpisodeToAir;
    }

    public String getTitle() {
        return title;
    }

    public NextEpisodeToAir getNextEpisodeToAir() {
        return nextEpisodeToAir;
    }

    public List<Network> getNetworks() {
        return new ArrayList<>(networks);
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public List<String> getOriginCountry() {
        return new ArrayList<>(originCountry);
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

    public List<Season> getSeasons() {
        return new ArrayList<>(seasons);
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public class CreatedBy {

        private int id;
        @SerializedName("credit_id")
        private String creditId;
        private String name;
        private int gender;
        @SerializedName("profile_path")
        private String profilePath;

        public int getId() {
            return id;
        }

        public String getCreditId() {
            return creditId;
        }

        public String getName() {
            return name;
        }

        public int getGender() {
            return gender;
        }

        public String getProfilePath() {
            return profilePath;
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

    public class LastEpisodeToAir {

        @SerializedName("air_date")
        private String airDate;
        @SerializedName("episode_number")
        private int episodeNumber;
        private int id;
        @SerializedName("name")
        private String title;
        @SerializedName("overview")
        private String plot;
        @SerializedName("production_code")
        private String productionCode;
        @SerializedName("season_number")
        private int seasonNumber;
        @SerializedName("show_id")
        private int showId;
        @SerializedName("still_path")
        private String stillPath;
        @SerializedName("vote_average")
        private int voteAverage;
        @SerializedName("vote_count")
        private int voteCount;

        public String getAirDate() {
            return airDate;
        }

        public int getEpisodeNumber() {
            return episodeNumber;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getPlot() {
            return plot;
        }

        public String getProductionCode() {
            return productionCode;
        }

        public int getSeasonNumber() {
            return seasonNumber;
        }

        public int getShowId() {
            return showId;
        }

        public String getStillPath() {
            return stillPath;
        }

        public int getVoteAverage() {
            return voteAverage;
        }

        public int getVoteCount() {
            return voteCount;
        }
    }

    public class NextEpisodeToAir extends LastEpisodeToAir {
    }

    public class Network {

        private String name;
        private int id;
        @SerializedName("logo_path")
        private String logoPath;
        @SerializedName("origin_country")
        private String originCountry;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public String getOriginCountry() {
            return originCountry;
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

    public class Season {

        @SerializedName("air_date")
        private String airDate;
        @SerializedName("episode_count")
        private int episodeCount;
        private int id;
        private String name;
        @SerializedName("overview")
        private String plot;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("season_number")
        private int seasonNumber;

        public String getAirDate() {
            return airDate;
        }

        public int getEpisodeCount() {
            return episodeCount;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPlot() {
            return plot;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public int getSeasonNumber() {
            return seasonNumber;
        }
    }
}
