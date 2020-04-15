package com.example.android.cinemary.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.cinemary.R;
import com.example.android.cinemary.models.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the RecyclerView in ReviewActivity which lists all reviews for a Movie.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private static final String TAG = ReviewAdapter.class.getSimpleName();
    private OnReviewClickListener onReviewClickListener;
    private List<Review> reviewList;
    private Context context;

    public ReviewAdapter(Context context, List<Review> reviewList, OnReviewClickListener onReviewClickListener) {
        this.onReviewClickListener = onReviewClickListener;
        this.reviewList = reviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_review, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final Review currentReview = reviewList.get(position);
        viewHolder.author.setText(context.getString(R.string.review_author, currentReview.getAuthor()));
        viewHolder.review.setText(currentReview.getReview());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView author, review;

        public ViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.review_list_author);
            review = itemView.findViewById(R.id.review_list_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onReviewClickListener.onReviewClick(getAdapterPosition());
        }
    }

    public interface OnReviewClickListener {
        void onReviewClick(int index);
    }

    public List<Review> getReviewList() {
        return new ArrayList<>(reviewList);
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    public void clear() {
        reviewList.clear();
        notifyDataSetChanged();
    }
}
