package com.example.android.cinemary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.cinemary.Constants;
import com.example.android.cinemary.R;
import com.example.android.cinemary.SettingsActivity;
import com.example.android.cinemary.adapters.ReviewAdapter;
import com.example.android.cinemary.models.Review;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements ReviewAdapter.OnReviewClickListener {

    ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        // Retrieve parcelable movie data
        Intent i = getIntent();
        ArrayList<Review> reviews = i.getExtras().
                getParcelableArrayList(Constants.PARCEL_KEY_REVIEW_LIST);
        String movieTitle = i.getExtras().getString(Constants.PARCEL_KEY_MOVIE_TITLE);

        setTitle(getString(R.string.review_title, movieTitle));

        RecyclerView recyclerView = findViewById(R.id.review_rv);

        reviewAdapter = new ReviewAdapter(this, reviews, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(reviewAdapter);
    }

    @Override
    public void onReviewClick(int index) {
        Review review = reviewAdapter.getReviewList().get(index);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(review.getWebUrl()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.review, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
