package com.example.android.cinemary.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Object which holds a Review made by a user about a Movie.
 */
public class Review implements Parcelable {

    private String _id, author, review, webUrl;

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return CONTENTS_FILE_DESCRIPTOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.author);
        dest.writeString(this.review);
        dest.writeString(this.webUrl);
    }

    /**
     * Constructor for Parcelable.
     */
    private Review(Parcel in) {
        this._id = in.readString();
        this.author = in.readString();
        this.review = in.readString();
        this.webUrl = in.readString();
    }

    /**
     * Constructor for standard Review Objects.
     */
    public Review(String _id, String author, String review, String webUrl) {
        this._id = _id;
        this.author = author;
        this.review = review;
        this.webUrl = webUrl;
    }

    public String get_id() {
        return _id;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    public String getWebUrl() {
        return webUrl;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + _id +
                ", author=" + author +
                ", review=" + review +
                ", webUrl=" + webUrl;
    }
}
