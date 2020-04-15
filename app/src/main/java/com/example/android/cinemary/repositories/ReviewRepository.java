package com.example.android.cinemary.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;
import android.util.Log;

import com.example.android.cinemary.AppExecutor;
import com.example.android.cinemary.BuildConfig;
import com.example.android.cinemary.data.requests.Converter;
import com.example.android.cinemary.data.requests.TheMovieDbApi;
import com.example.android.cinemary.data.requests.TheMovieDbRetrofit;
import com.example.android.cinemary.data.requests.responses.Reviews;
import com.example.android.cinemary.models.Review;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository for Reviews.
 */
public class ReviewRepository {

    private static final String TAG = ReviewRepository.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String RESPONSE_CODE = "Response code: ";
    private static ReviewRepository instance;
    private MutableLiveData<List<Review>> reviews;

    public static ReviewRepository getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new ReviewRepository();
            }
        }
        return instance;
    }

    private ReviewRepository() {
        reviews = new MutableLiveData<>();
    }

    public LiveData<List<Review>> getMovieReviews(final int tmdbId) {
        Log.d(TAG, "Retrieving Reviews for Movie id: " + tmdbId);
        AppExecutor.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                TheMovieDbApi theMovieDbApi = TheMovieDbRetrofit.getRetrofit().create(TheMovieDbApi.class);
                Call<Reviews> call = theMovieDbApi.getMovieReviews(tmdbId, BuildConfig.API_KEY);
                call.enqueue(new Callback<Reviews>() {
                    @Override
                    public void onResponse(@NonNull Call<Reviews> call, @NonNull Response<Reviews> response) {
                        if (response.code() != HttpURLConnection.HTTP_OK) {
                            Log.d(TAG, RESPONSE_CODE + response.code());
                            return;
                        }
                        if (response.body() != null) {
                            reviews.postValue(Converter.reviewsToList(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Reviews> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        return reviews;
    }

    public LiveData<List<Review>> getTvReviews(final int tmdbId) {
        Log.d(TAG, "Retrieving Reviews for TV id: " + tmdbId);
        AppExecutor.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                TheMovieDbApi theMovieDbApi = TheMovieDbRetrofit.getRetrofit().create(TheMovieDbApi.class);
                Call<Reviews> call = theMovieDbApi.getTvReviews(tmdbId, BuildConfig.API_KEY);
                call.enqueue(new Callback<Reviews>() {
                    @Override
                    public void onResponse(@NonNull Call<Reviews> call, @NonNull Response<Reviews> response) {
                        if (response.code() != HttpURLConnection.HTTP_OK) {
                            Log.d(TAG, RESPONSE_CODE + response.code());
                            return;
                        }
                        if (response.body() != null) {
                            reviews.postValue(Converter.reviewsToList(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Reviews> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        return reviews;
    }
}
