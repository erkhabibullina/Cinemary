package com.example.android.cinemary.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.cinemary.R;
import com.example.android.cinemary.models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the RecyclerView in MainActivity which lists all Movies.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static final String TAG = "MovieAdapter";
    private OnMovieClickListener onMovieClickListener;
    private List<Movie> movieList;
    private Context context;

    public MovieAdapter(Context context, List<Movie> movieList, OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_show, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final Movie currentMovie = movieList.get(position);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean showRatings = sharedPreferences.getBoolean(
                context.getString(R.string.prefs_poster_list_rating_key),
                context.getResources().getBoolean(R.bool.prefs_poster_list_rating_default));

        if (showRatings) {
            viewHolder.voteAverage_iv.setVisibility(View.VISIBLE);
            viewHolder.voteAverage_tv.setVisibility(View.VISIBLE);
            viewHolder.voteAverage_tv.setText(String.valueOf(currentMovie.getVoteAverage()));
        } else {
            viewHolder.voteAverage_iv.setVisibility(View.INVISIBLE);
            viewHolder.voteAverage_tv.setVisibility(View.INVISIBLE);
        }

        Glide.with(context).load(currentMovie.getPosterUrl()).into(viewHolder.poster_iv);

//        Picasso.get().load(currentMovie.getPosterUrl()).into(viewHolder.poster_iv,
//                new com.squareup.picasso.Callback() {
//                    @Override
//                    public void onSuccess() {
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        viewHolder.poster_iv.setVisibility(View.INVISIBLE);
//                        viewHolder.voteAverage_iv.setVisibility(View.INVISIBLE);
//                        viewHolder.voteAverage_tv.setVisibility(View.INVISIBLE);
//                    }
//                });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView poster_iv, voteAverage_iv;
        private TextView voteAverage_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            poster_iv = itemView.findViewById(R.id.poster_iv);
            voteAverage_iv = itemView.findViewById(R.id.vote_average_iv);
            voteAverage_tv = itemView.findViewById(R.id.vote_average_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMovieClickListener.onMovieClick(getAdapterPosition());
        }
    }

    public interface OnMovieClickListener {
        void onMovieClick(int index);
    }

    public List<Movie> getMovieList() {
        return new ArrayList<>(movieList);
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public void clear() {
        movieList.clear();
        notifyDataSetChanged();
    }
}
