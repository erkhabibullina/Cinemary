package com.example.android.cinemary;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.example.android.cinemary.R;
import com.example.android.cinemary.adapters.MovieAdapter;
import com.example.android.cinemary.models.Movie;
import com.example.android.cinemary.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static int sortBy, currentPage;
    private int gridLayoutSpanCount, gridLayoutOrientation;
    private boolean isScrolling;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private TextView favoritesEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name) + ": " +
                getString(R.string.sort_by_popular));

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        recyclerView = findViewById(R.id.recyclerview);
        favoritesEmpty = findViewById(R.id.favorites_empty);

        // Change grid span/orientation depending on screen orientation
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_UNDEFINED:
            case Configuration.ORIENTATION_PORTRAIT:
                gridLayoutSpanCount = 2;
                gridLayoutOrientation = GridLayoutManager.VERTICAL;
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                gridLayoutSpanCount = 1;
                gridLayoutOrientation = GridLayoutManager.HORIZONTAL;
                break;
        }

        movieAdapter = new MovieAdapter(this, new ArrayList<Movie>(), this);
        gridLayoutManager = new GridLayoutManager(this, gridLayoutSpanCount);
        gridLayoutManager.setOrientation(gridLayoutOrientation);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set swiperefresh to allow updating of list by scrolling up
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (sortBy == Constants.SORTBY_FAVORTITES) {
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    setLiveData(1);
                }
            }
        });
        swipeRefreshLayout.setRefreshing(true);

        // Set scroll listener (for pagination)
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int itemCount = gridLayoutManager.getItemCount();
                int lastVisible = gridLayoutManager.findLastVisibleItemPosition();

                boolean end = lastVisible + 5 >= itemCount;
                if(isScrolling && itemCount > 0 && end) {
                    isScrolling = false;

                    // Update list
                    setLiveData(++currentPage);
                }
            }
        });

        // Set correct title
        switch (sortBy) {
            case Constants.SORTBY_POPULAR:
                setTitle(getString(R.string.app_name) +
                        ": " + getString(R.string.sort_by_popular));
                break;
            case Constants.SORTBY_TOPRATED:
                setTitle(getString(R.string.app_name) +
                        ": " + getString(R.string.sort_by_highest_rated));
                break;
            case Constants.SORTBY_FAVORTITES:
                setTitle(getString(R.string.app_name) +
                        ": " + getString(R.string.sort_by_favorites));
                break;
        }

        sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        subscribeObservers();
    }

    @Override
    public void onMovieClick(int index) {
        Movie clickedMovie = movieAdapter.getMovieList().get(index);
        Intent i = new Intent(MainActivity.this, MovieDetailActivity.class);
        i.putExtra(Constants.PARCEL_KEY_MOVIE, clickedMovie);
        startActivity(i);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.prefs_poster_list_rating_key))) {
            movieAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Set ViewModel Observers. This will look at the value of sortBy, which is the selector
     * for what to display in the RecyclerView Adapter. For this there are two Observers,
     * one which handles the MutableLiveData fetched via Retrofit2 (popular/toprated) and
     * the LiveData from the database (favorites).
     */
    private void subscribeObservers() {
        recyclerView.setVisibility(View.VISIBLE);
        favoritesEmpty.setVisibility(View.GONE);
        switch (sortBy) {
            case Constants.SORTBY_FAVORTITES:
                // Remove other Observer
                if (viewModel.getMovies() != null && viewModel.getMovies().hasObservers()) {
                    viewModel.getMovies().removeObservers(this);
                }
                // Initial LiveData
                if (viewModel.getFavorites() == null) {
                    setLiveData(1);
                }
                // Set Observer
                viewModel.getFavorites().observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(@Nullable List<Movie> favorites) {
                        swipeRefreshLayout.setRefreshing(false);
                        if (favorites != null && !favorites.isEmpty()) {
                            movieAdapter.setMovieList(favorites);
                        } else {
                            recyclerView.setVisibility(View.INVISIBLE);
                            favoritesEmpty.setVisibility(View.VISIBLE);
                        }
                    }
                });
                break;
            default:
                // Remove other Observer
                if (viewModel.getFavorites() != null && viewModel.getFavorites().hasObservers()) {
                    viewModel.getFavorites().removeObservers(this);
                }
                // Initial LiveData
                if (viewModel.getMovies() == null) {
                    setLiveData(1);
                }
                // Set Observer
                viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(@Nullable List<Movie> movies) {
                        swipeRefreshLayout.setRefreshing(false);
                        movieAdapter.setMovieList(movies);
                    }
                });
        }
    }

    /**
     * Set LiveData (popular/toprated/favorites).
     *
     * @param page Page number, used for pagination in Retrofit2 calls.
     */
    private void setLiveData(int page) {
        viewModel.setLiveData(sortBy, page);
        if (sortBy != Constants.SORTBY_FAVORTITES) {
            currentPage = page;
            Log.d(TAG, "Current page: " + currentPage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_by:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.sort_by))
                        .setItems(R.array.sort_by_entries, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int index) {
                                String title = null;
                                switch (index) {
                                    case 0:
                                        sortBy = Constants.SORTBY_POPULAR;
                                        title = getString(R.string.sort_by_popular);
                                        setLiveData(1);
                                        break;
                                    case 1:
                                        sortBy = Constants.SORTBY_TOPRATED;
                                        title = getString(R.string.sort_by_highest_rated);
                                        setLiveData(1);
                                        break;
                                    case 2:
                                        sortBy = Constants.SORTBY_FAVORTITES;
                                        title = getString(R.string.sort_by_favorites);
                                        break;
                                }
                                setTitle(getString(R.string.app_name) + ": " + title);
                                recyclerView.scrollToPosition(0);
                                subscribeObservers();
                            }
                        });
                builder.create().show();
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}