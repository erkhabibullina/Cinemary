package com.example.android.cinemary.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.android.cinemary.Constants;
import com.example.android.cinemary.models.Movie;
import com.example.android.cinemary.repositories.MovieRepository;

import java.util.List;

/**
 * LiveData ViewModel for MainActivity.
 */
public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<List<Movie>> movies;
    private LiveData<List<Movie>> favorites;
    private Application application;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        this.movieRepository = MovieRepository.getInstance();
    }

    /**
     * Get the movies LiveData, which will contain either Popular of TopRated.
     */
    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    /**
     * Get the favorite movies LiveData, this is a direct connection to the database.
     */
    public LiveData<List<Movie>> getFavorites() {
        return favorites;
    }

    /**
     * Set LiveData, only allow the initial call to database.
     *
     * @param sortBy Essentially what list type is clicked in the AlertDialog.
     * @param page   Page number, used for pagination in retrofit calls.
     */
    public void setLiveData(int sortBy, int page) {
        switch (sortBy) {
            case Constants.SORTBY_POPULAR:
                movies = movieRepository.getPopular(page);
                break;
            case Constants.SORTBY_TOPRATED:
                movies = movieRepository.getTopRated(page);
                break;
            case Constants.SORTBY_FAVORTITES:
                if (favorites == null) {
                    favorites = movieRepository.getFavorites(application.getApplicationContext());
                }
                break;
        }
    }
}
