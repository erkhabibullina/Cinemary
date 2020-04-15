package com.example.android.cinemary.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.android.cinemary.models.Movie;
import com.example.android.cinemary.models.Review;
import com.example.android.cinemary.models.Video;
import com.example.android.cinemary.repositories.MovieRepository;
import com.example.android.cinemary.repositories.ReviewRepository;
import com.example.android.cinemary.repositories.VideoRepository;

import java.util.List;

/**
 * LiveData ViewModel for MovieDetailActivity.
 */
public class MovieDetailActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private ReviewRepository reviewRepository;
    private VideoRepository videoRepository;
    private LiveData<Movie> movie;
    private LiveData<List<Review>> reviews;
    private LiveData<List<Video>> videos;
    private Application application;

    public MovieDetailActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        this.movieRepository = MovieRepository.getInstance();
        this.reviewRepository = ReviewRepository.getInstance();
        this.videoRepository = VideoRepository.getInstance();
    }

    /**
     * Get the Movie LiveData (singe movie).
     */
    public LiveData<Movie> getMovie() {
        return movie;
    }

    /**
     * Get the Review LiveData (list).
     */
    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    /**
     * Get the Video LiveData (list).
     */
    public LiveData<List<Video>> getVideos() {
        return videos;
    }

    /**
     * Set LiveData Objects accordingly, but only if they are null (to avoid recalling).
     *
     * @param tmdbId TMDB Movie ID.
     */
    public void setLiveData(int tmdbId) {
        if (movie == null) {
            movie = movieRepository.getFavorite(application.getApplicationContext(), tmdbId);
        }
        if (reviews == null) {
            reviews = reviewRepository.getMovieReviews(tmdbId);
        }
        if (videos == null) {
            videos = videoRepository.getMovieVideos(tmdbId);
        }
    }
}
