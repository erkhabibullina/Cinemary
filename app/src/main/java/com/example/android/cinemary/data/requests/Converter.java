package com.example.android.cinemary.data.requests;

import com.example.android.cinemary.data.requests.responses.Reviews;
import com.example.android.cinemary.data.requests.responses.Videos;
import com.example.android.cinemary.data.requests.responses.movies.MoviesPopular;
import com.example.android.cinemary.data.requests.responses.movies.MoviesTopRated;
import com.example.android.cinemary.models.Movie;
import com.example.android.cinemary.models.Review;
import com.example.android.cinemary.models.Video;


import java.util.ArrayList;

/**
 * Converts responses from the API into used Objects with
 * values such as full URL to poster or backdrop images.
 */
public abstract class Converter {

    private static final String TAG = Converter.class.getSimpleName();
    private static final String BASE_URL_POSTER = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    private static final String BASE_URL_BACKDROP = "https://image.tmdb.org/t/p/original";
    private static final String BASE_URL_PROFILE = "https://image.tmdb.org/t/p/original";
    private static final String BASE_URL_WEB_MOVIE = "https://themoviedb.org/movie/";
    private static final String BASE_URL_WEB_TV = "https://themoviedb.org/tv/";
    private static final String BASE_URL_YOUTUBE = "https://youtube.com/watch?v=";

    public static ArrayList<Movie> popularMoviesToList(MoviesPopular popular) {
        ArrayList<MoviesPopular.Results> rawShows = new ArrayList<>(popular.getResults());
        ArrayList<Movie> movies = new ArrayList<>();
        for (MoviesPopular.Results i : rawShows) {
            movies.add(new Movie(
                    i.getTmdbId(),
                    i.getTitle(),
                    i.getOriginalTitle(),
                    i.getPlot(),
                    i.getOriginalLanguage(),
                    i.getReleaseDate(),
                    getFinalPosterUrl(i.getPosterPath()),
                    getFinalBackdropUrl(i.getBackdropPath()),
                    getFinalMovieWebUrl(i.getTmdbId()),
                    i.getVoteCount(),
                    i.getVoteAverage(),
                    i.getPopularity(),
                    i.getGenreIds()));
        }
        return movies;
    }

    public static ArrayList<Movie> topRatedMoviesToList(MoviesTopRated topRated) {
        ArrayList<MoviesTopRated.Results> rawShows = new ArrayList<>(topRated.getResults());
        ArrayList<Movie> movies = new ArrayList<>();
        for (MoviesPopular.Results i : rawShows) {
            movies.add(new Movie(
                    i.getTmdbId(),
                    i.getTitle(),
                    i.getOriginalTitle(),
                    i.getPlot(),
                    i.getOriginalLanguage(),
                    i.getReleaseDate(),
                    getFinalPosterUrl(i.getPosterPath()),
                    getFinalBackdropUrl(i.getBackdropPath()),
                    getFinalMovieWebUrl(i.getTmdbId()),
                    i.getVoteCount(),
                    i.getVoteAverage(),
                    i.getPopularity(),
                    i.getGenreIds()));
        }
        return movies;
    }

    public static ArrayList<Review> reviewsToList(Reviews reviews) {
        ArrayList<Reviews.Results> rawReviews = new ArrayList<>(reviews.getResults());
        ArrayList<Review> reviewList = new ArrayList<>();
        for (Reviews.Results i : rawReviews) {
            reviewList.add(new Review(i.getId(),
                    i.getAuthor(),
                    i.getReview(),
                    i.getWebUrl()));
        }
        return reviewList;
    }

    public static ArrayList<Video> videosToList(Videos videos) {
        ArrayList<Videos.Results> rawVideos = new ArrayList<>(videos.getResults());
        ArrayList<Video> videoList = new ArrayList<>();
        for (Videos.Results i : rawVideos) {
            videoList.add(new Video(i.getId(),
                    i.getName(),
                    i.getType(),
                    getFinalVideoWebUrl(i.getSite(), i.getKey())));
        }
        return videoList;
    }

    private static String getFinalPosterUrl(String posterPath) {
        return BASE_URL_POSTER + posterPath;
    }

    private static String getFinalBackdropUrl(String backdropPath) {
        return BASE_URL_BACKDROP + backdropPath;
    }

    private static String getFinalMovieWebUrl(int tmdbId) {
        return BASE_URL_WEB_MOVIE + tmdbId;
    }

    private static String getFinalVideoWebUrl(String site, String key) {
        switch (site.toLowerCase()) {
            case "youtube":
                return BASE_URL_YOUTUBE + key;
        }
        return null;
    }
}