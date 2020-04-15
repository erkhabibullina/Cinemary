package com.example.android.cinemary.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.cinemary.data.database.IntegerListConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Object which holds information about a Movie.
 */
@Entity(tableName = "movie")
public class Movie implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "tmdb_id")
    private int tmdbId;

    @ColumnInfo(name = "vote_count")
    private int voteCount;

    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @ColumnInfo(name = "original_language")
    private String originalLanguage;

    private String plot;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "poster_url")
    private String posterUrl;

    @ColumnInfo(name = "backdrop_url")
    private String backdropUrl;

    @ColumnInfo(name = "web_url")
    private String webUrl;

    @ColumnInfo(name = "vote_average")
    private double voteAverage;

    @ColumnInfo(name = "genre_ids")
    @TypeConverters(IntegerListConverter.class)
    private List<Integer> genreIds;

    private String title;
    private double popularity;

    @Ignore
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return CONTENTS_FILE_DESCRIPTOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.tmdbId);
        dest.writeInt(this.voteCount);
        dest.writeDouble(this.voteAverage);
        dest.writeDouble(this.popularity);
        dest.writeString(this.title);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.plot);
        dest.writeString(this.releaseDate);
        dest.writeString(this.posterUrl);
        dest.writeString(this.backdropUrl);
        dest.writeString(this.webUrl);
        dest.writeList(this.genreIds);
    }

    /**
     * Constructor for Parcelable.
     */
    @Ignore
    private Movie(Parcel in) {
        this.tmdbId = in.readInt();
        this.voteCount = in.readInt();
        this.voteAverage = in.readDouble();
        this.popularity = in.readDouble();
        this.title = in.readString();
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.plot = in.readString();
        this.releaseDate = in.readString();
        this.posterUrl = in.readString();
        this.backdropUrl = in.readString();
        this.webUrl = in.readString();
        this.genreIds = new ArrayList<>();
        in.readList(genreIds, Movie.class.getClassLoader());
    }

    /**
     * Constructor for standard Movie Objects.
     */
    public Movie(int tmdbId, String title, String originalTitle, String plot, String originalLanguage,
                 String releaseDate, String posterUrl, String backdropUrl, String webUrl,
                 int voteCount, double voteAverage, double popularity, List<Integer> genreIds) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.originalTitle = originalTitle;
        this.plot = plot;
        this.originalLanguage = originalLanguage;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.backdropUrl = backdropUrl;
        this.webUrl = webUrl;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.genreIds = genreIds;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getPlot() {
        return plot;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public List<Integer> getGenreIds() {
        return new ArrayList<>(genreIds);
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setBackdropUrl(String backdropUrl) {
        this.backdropUrl = backdropUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "tmdbId=" + tmdbId +
                ", voteCount=" + voteCount +
                ", originalTitle='" + originalTitle + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", plot='" + plot + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", backdropUrl='" + backdropUrl + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", voteAverage=" + voteAverage +
                ", genreIds=" + genreIds +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}
