package com.example.android.cinemary.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.cinemary.models.Movie;

import java.util.List;

@Dao
public interface FavoriteMovieDao {

    @Query("SELECT * FROM movie ORDER BY title")
    LiveData<List<Movie>> getFavoriteMovieList();

    // Search by TMDB id
    @Query("SELECT * FROM movie WHERE tmdb_id = :tmdbId")
    LiveData<Movie> getFavoriteMovieById(int tmdbId);

    @Insert
    void insertFavoriteMovie(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavoriteMovie(Movie movie);

    @Delete
    void deleteFavoriteMovie(Movie movie);
}
