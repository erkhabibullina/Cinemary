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
import com.example.android.cinemary.data.requests.responses.Videos;
import com.example.android.cinemary.models.Video;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository for Videos (trailer/clip).
 */
public class VideoRepository {

    private static final String TAG = VideoRepository.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String RESPONSE_CODE = "Response code: ";
    private static VideoRepository instance;
    private MutableLiveData<List<Video>> videos;

    public static VideoRepository getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new VideoRepository();
            }
        }
        return instance;
    }

    private VideoRepository() {
        videos = new MutableLiveData<>();
    }

    public LiveData<List<Video>> getMovieVideos(final int tmdbId) {
        Log.d(TAG, "Retrieving Videos for Movie id: " + tmdbId);
        AppExecutor.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                TheMovieDbApi theMovieDbApi = TheMovieDbRetrofit.getRetrofit().create(TheMovieDbApi.class);
                Call<Videos> call = theMovieDbApi.getMovieVideos(tmdbId, BuildConfig.API_KEY);
                call.enqueue(new Callback<Videos>() {
                    @Override
                    public void onResponse(@NonNull Call<Videos> call, @NonNull Response<Videos> response) {
                        if (response.code() != HttpURLConnection.HTTP_OK) {
                            Log.d(TAG, RESPONSE_CODE + response.code());
                            return;
                        }
                        if (response.body() != null) {
                            videos.postValue(Converter.videosToList(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Videos> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        return videos;
    }

    public LiveData<List<Video>> getTvVideos(final int tmdbId) {
        Log.d(TAG, "Retrieving Videos for Tv id: " + tmdbId);
        AppExecutor.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                TheMovieDbApi theMovieDbApi = TheMovieDbRetrofit.getRetrofit().create(TheMovieDbApi.class);
                Call<Videos> call = theMovieDbApi.getTvVideos(tmdbId, BuildConfig.API_KEY);
                call.enqueue(new Callback<Videos>() {
                    @Override
                    public void onResponse(@NonNull Call<Videos> call, @NonNull Response<Videos> response) {
                        if (response.code() != HttpURLConnection.HTTP_OK) {
                            Log.d(TAG, RESPONSE_CODE + response.code());
                            return;
                        }
                        if (response.body() != null) {
                            videos.postValue(Converter.videosToList(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Videos> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        return videos;
    }
}
