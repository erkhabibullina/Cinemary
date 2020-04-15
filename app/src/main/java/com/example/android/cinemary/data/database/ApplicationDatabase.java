package com.example.android.cinemary.data.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.example.android.cinemary.models.Movie;


/**
 * Database instance.
 */
@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static final String TAG = ApplicationDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "moviedb";
    private static ApplicationDatabase instance;

    public static ApplicationDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Creating new database instance.");
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        ApplicationDatabase.class,
                        ApplicationDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(TAG, "Getting database instance.");
        return instance;
    }

    public abstract FavoriteMovieDao favoriteMovieDao();
}
