package com.example.android.cinemary.data.requests.responses.people;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PeoplePopular {

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

        private double popularity;
        private int id;
        @SerializedName("profile_path")
        private String profilePath;
        private String name;
        private List<KnownFor> knownFor;
        private boolean adult;

        public double getPopularity() {
            return popularity;
        }

        public int getId() {
            return id;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public String getName() {
            return name;
        }

        public List<KnownFor> getKnownFor() {
            return new ArrayList<>(knownFor);
        }

        public boolean isAdult() {
            return adult;
        }

        public class KnownFor {

            @SerializedName("original_name")
            private String originalTitle;
            private int id;
            @SerializedName("media_type")
            private String mediaType;
            @SerializedName("name")
            private String title;
            @SerializedName("vote_count")
            private int voteCount;
            @SerializedName("vote_average")
            private double voteAverage;
            @SerializedName("poster_path")
            private String posterPath;
            @SerializedName("first_air_date")
            private String firstAirDate;
            private double popularity;
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

            public String getOriginalTitle() {
                return originalTitle;
            }

            public int getId() {
                return id;
            }

            public String getMediaType() {
                return mediaType;
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

            public String getPosterPath() {
                return posterPath;
            }

            public String getFirstAirDate() {
                return firstAirDate;
            }

            public double getPopularity() {
                return popularity;
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
        }
    }
}
