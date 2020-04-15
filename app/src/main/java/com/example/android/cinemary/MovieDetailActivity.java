package com.example.android.cinemary;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.net.Uri;
import androidx.annotation.Nullable;

import com.example.android.cinemary.databinding.ActivityMovieDetailBinding;
import com.example.android.cinemary.models.Movie;
import com.example.android.cinemary.models.Review;
import com.example.android.cinemary.models.Video;
import com.example.android.cinemary.repositories.MovieRepository;
import com.example.android.cinemary.viewmodels.MovieDetailActivityViewModel;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ShareCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for details about the clicked Movie.
 */
public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    private ActivityMovieDetailBinding dataBinding;
    private MovieDetailActivityViewModel viewModel;
    private MovieRepository movieRepository;
    private Movie movie;
    private MenuItem addToFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        movieRepository = MovieRepository.getInstance();

        // Retrieve parcelable movie data
        Intent i = getIntent();
        movie = i.getExtras().getParcelable(Constants.PARCEL_KEY_MOVIE);

        // Set data to fields and load images
        dataBinding.titleTv.setText(movie.getTitle());
        dataBinding.plotTv.setText(movie.getPlot());
        dataBinding.scoreTv.setText(getString(R.string.score_details, String.valueOf(movie.getVoteAverage()),
                String.valueOf(movie.getVoteCount())));
        dataBinding.releaseDateTv.setText(movie.getReleaseDate());
        dataBinding.languageTv.setText(movie.getOriginalLanguage());
        dataBinding.voteAverageTv.setText(String.valueOf(movie.getVoteAverage()));
        Glide.with(this).load(movie.getPosterUrl()).into(dataBinding.posterIv);
        Glide.with(this).load(movie.getBackdropUrl()).into(dataBinding.backdropIv);

        // Set title
        setTitle(movie.getTitle());

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this).get(MovieDetailActivityViewModel.class);
        viewModel.setLiveData(movie.getTmdbId());

        // Set OnClickListeners
        dataBinding.review.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == dataBinding.review) {
            Intent intent = new Intent(MovieDetailActivity.this, ReviewActivity.class);
            intent.putParcelableArrayListExtra(Constants.PARCEL_KEY_REVIEW_LIST,
                    (ArrayList<Review>) viewModel.getReviews().getValue());
            intent.putExtra(Constants.PARCEL_KEY_MOVIE_TITLE, movie.getTitle());
            startActivity(intent);
        }
    }

    /**
     * Set ViewModel Observers.
     */
    private void subscribeObservers() {
        viewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                // Change star icon when movie is added or removed from favorites
                if (movie != null) {
                    addToFavorites.setIcon(R.drawable.ic_star);
                    addToFavorites.setTitle(R.string.favorite_remove);
                } else {
                    addToFavorites.setIcon(R.drawable.ic_star_border);
                    addToFavorites.setTitle(R.string.favorite_add);
                }
            }
        });
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(@Nullable List<Review> reviews) {
                if (reviews == null || reviews.isEmpty()) {
                    dataBinding.reviewLayout.setVisibility(View.GONE);
                } else {
                    dataBinding.reviewLayout.setVisibility(View.VISIBLE);
                    dataBinding.review.setText(reviews.get(0).getReview());
                }
            }
        });
        viewModel.getVideos().observe(this, new Observer<List<Video>>() {
            @Override
            public void onChanged(@Nullable List<Video> videos) {
                if (videos != null && !videos.isEmpty()) {
                    dataBinding.trailerLayout.setVisibility(View.VISIBLE);

                    // Clear out all old views
                    dataBinding.trailerLayoutList.removeAllViews();

                    // Load in new views
                    for (final Video video : videos) {
                        if (video.getType().equals(Constants.VIDEO_TRAILER_IDENTIFIER)) {
                            View trailerLayout = getLayoutInflater().inflate(R.layout.list_trailer, null);
                            TextView title = trailerLayout.findViewById(R.id.trailer_title);
                            title.setText(video.getTitle());
                            trailerLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW,
                                            Uri.parse(video.getWebUrl()));
                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                        startActivity(intent);
                                    }
                                }
                            });
                            dataBinding.trailerLayoutList.addView(trailerLayout);
                        }
                    }
                } else {
                    dataBinding.trailerLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * Calls the database to add the movie and displays snackbar.
     */
    private void addMovieToFavorites() {
        movieRepository.addFavorite(getApplicationContext(), movie);
        String favoriteAdded = getString(R.string.favorite_added_snack,
                movie.getTitle());
        Snackbar.make(dataBinding.getRoot(), favoriteAdded, Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(R.color.colorAccent))
                .setAction(getResources().getString(R.string.undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieRepository.removeFavorite(getApplicationContext(), movie);
                    }
                })
                .show();
    }

    /**
     * Calls the database to remove the movie and displays snackbar.
     */
    private void removeMovieFromFavorites() {
        movieRepository.removeFavorite(getApplicationContext(), movie);
        String favoriteRemoved = getString(R.string.favorite_removed_snack,
                movie.getTitle());
        Snackbar.make(dataBinding.getRoot(), favoriteRemoved, Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(R.color.colorAccent))
                .setAction(getResources().getString(R.string.undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieRepository.addFavorite(getApplicationContext(), movie);
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_detail, menu);
        /* Subscribe Observers here because menu item MUST be loaded before Observers
         * because the favorite star icon is located in the toolbar. */
        addToFavorites = menu.findItem(R.id.action_favorite);
        subscribeObservers();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_favorite:
                if (viewModel.getMovie().getValue() != null) { // Movie is in favorites
                    removeMovieFromFavorites();
                } else { // Movie is not in favorites
                    addMovieToFavorites();
                }
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_share:
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType(getString(R.string.share_mime_type))
                        .setChooserTitle(getString(R.string.share_title, movie.getTitle()))
                        .setText(getString(R.string.share_text, movie.getTitle(),
                                String.valueOf(movie.getVoteAverage()), movie.getWebUrl()))
                        .startChooser();
                return true;
            case R.id.action_weblink:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getWebUrl()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
