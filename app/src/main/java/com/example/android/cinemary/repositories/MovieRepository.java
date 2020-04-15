package com.example.android.cinemary.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import androidx.annotation.NonNull;
import android.util.Log;

import com.example.android.cinemary.AppExecutor;
import com.example.android.cinemary.BuildConfig;
import com.example.android.cinemary.data.database.AppDatabase;
import com.example.android.cinemary.data.requests.Converter;
import com.example.android.cinemary.data.requests.TheMovieDbApi;
import com.example.android.cinemary.data.requests.TheMovieDbRetrofit;
import com.example.android.cinemary.data.requests.responses.movies.MoviesPopular;
import com.example.android.cinemary.data.requests.responses.movies.MoviesTopRated;
import com.example.android.cinemary.models.Movie;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository for Movies, can fetch from internet and database (favorites).
 */
public class MovieRepository {

    private static final String TAG = MovieRepository.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String RESPONSE_CODE = "Response code: ";
    private static MovieRepository instance;
    private MutableLiveData<List<Movie>> mutableMovies;

    public static MovieRepository getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new MovieRepository();
            }
        }
        return instance;
    }

    private MovieRepository() {
        mutableMovies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getPopular(final int page) {
        Log.d(TAG, "Retrieving Popular Movies, page " + page);
        AppExecutor.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                TheMovieDbApi theMovieDbApi = TheMovieDbRetrofit.getRetrofit().create(TheMovieDbApi.class);
                Call<MoviesPopular> call = theMovieDbApi.getMoviesPopular(BuildConfig.API_KEY, page <= 1 ? 1 : page);
                call.enqueue(new Callback<MoviesPopular>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesPopular> call, @NonNull Response<MoviesPopular> response) {
                        if (response.code() != HttpURLConnection.HTTP_OK) {
                            Log.d(TAG, RESPONSE_CODE + response.code());
                            return;
                        }
                        if (response.body() != null) {
                            ArrayList<Movie> movieList = Converter.popularMoviesToList(response.body());
                            if (page <= 1) {
                                mutableMovies.postValue(movieList);
                            } else if (mutableMovies != null) {
                                List<Movie> currentList = mutableMovies.getValue();
                                if (currentList != null) {
                                    currentList.addAll(movieList);
                                }
                                mutableMovies.postValue(currentList);
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MoviesPopular> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        return mutableMovies;
    }

    public LiveData<List<Movie>> getTopRated(final int page) {
        Log.d(TAG, "Retrieving Top Rated Movies, page " + page);
        AppExecutor.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                TheMovieDbApi theMovieDbApi = TheMovieDbRetrofit.getRetrofit().create(TheMovieDbApi.class);
                Call<MoviesTopRated> call = theMovieDbApi.getMoviesTopRated(BuildConfig.API_KEY, page <= 1 ? 1 : page);
                call.enqueue(new Callback<MoviesTopRated>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesTopRated> call, @NonNull Response<MoviesTopRated> response) {
                        if (response.code() != HttpURLConnection.HTTP_OK) {
                            Log.d(TAG, RESPONSE_CODE + response.code());
                            return;
                        }
                        if (response.body() != null) {
                            ArrayList<Movie> movieList = Converter.topRatedMoviesToList(response.body());
                            if (page <= 1) {
                                mutableMovies.postValue(movieList);
                            } else if (mutableMovies != null) {
                                List<Movie> currentList = mutableMovies.getValue();
                                if (currentList != null) {
                                    currentList.addAll(movieList);
                                }
                                mutableMovies.postValue(currentList);
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MoviesTopRated> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        return mutableMovies;
    }

    public LiveData<List<Movie>> getFavorites(final Context context) {
        Log.d(TAG, "Retrieving favorites");
        return AppDatabase.getInstance(context).favoriteMovieDao().getFavoriteMovieList();
    }

    public LiveData<Movie> getFavorite(final Context context, final int tmdbId) {
        return AppDatabase.getInstance(context).favoriteMovieDao().getFavoriteMovieById(tmdbId);
    }

    public void addFavorite(final Context context, final Movie movie) {
        Log.d(TAG, "Adding '" + movie.getTitle() + "' to favorites");
        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(context).favoriteMovieDao().insertFavoriteMovie(movie);
            }
        });

    }

    public void removeFavorite(final Context context, final Movie movie) {
        Log.d(TAG, "Removing '" + movie.getTitle() + "' from favorites");
        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(context).favoriteMovieDao().deleteFavoriteMovie(movie);
            }
        });
    }
}
