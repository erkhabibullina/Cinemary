package com.example.android.cinemary.data.requests;

import com.example.android.cinemary.data.requests.responses.Credits;
import com.example.android.cinemary.data.requests.responses.Genres;
import com.example.android.cinemary.data.requests.responses.Images;
import com.example.android.cinemary.data.requests.responses.Reviews;
import com.example.android.cinemary.data.requests.responses.Videos;
import com.example.android.cinemary.data.requests.responses.movies.MovieDetails;
import com.example.android.cinemary.data.requests.responses.movies.MoviesPopular;
import com.example.android.cinemary.data.requests.responses.movies.MoviesSimilar;
import com.example.android.cinemary.data.requests.responses.movies.MoviesTopRated;
import com.example.android.cinemary.data.requests.responses.people.PeopleDetails;
import com.example.android.cinemary.data.requests.responses.people.PeoplePopular;
import com.example.android.cinemary.data.requests.responses.tv.TvDetails;
import com.example.android.cinemary.data.requests.responses.tv.TvPopular;
import com.example.android.cinemary.data.requests.responses.tv.TvSimilar;
import com.example.android.cinemary.data.requests.responses.tv.TvTopRated;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit caller, returns correlating response object.
 */
public interface TheMovieDbApi {

    /**
     * API Movie calls
     */
    @GET(Api.MOVIES + Api.POPULAR)
    Call<MoviesPopular> getMoviesPopular(
            @Query(Api.KEY) String apiKey,
            @Query(Api.PAGE) int page);

    @GET(Api.MOVIES + Api.TOP_RATED)
    Call<MoviesTopRated> getMoviesTopRated(
            @Query(Api.KEY) String apiKey,
            @Query(Api.PAGE) int page);

    @GET(Api.MOVIES + Api.ID_PARAM)
    Call<MovieDetails> getMovieDetails(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.MOVIES + Api.ID_PARAM + Api.IMAGES)
    Call<Images> getMovieImages(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.MOVIES + Api.ID_PARAM + Api.VIDEOS)
    Call<Videos> getMovieVideos(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.MOVIES + Api.ID_PARAM + Api.REVIEWS)
    Call<Reviews> getMovieReviews(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.MOVIES + Api.ID_PARAM + Api.CREDITS)
    Call<Credits> getMovieCredits(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.MOVIES + Api.ID_PARAM + Api.SIMILAR)
    Call<MoviesSimilar> getMovieSimilar(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    /**
     * API TV calls
     */
    @GET(Api.TV + Api.POPULAR)
    Call<TvPopular> getTvPopular(
            @Query(Api.KEY) String apiKey,
            @Query(Api.PAGE) int page);

    @GET(Api.TV + Api.TOP_RATED)
    Call<TvTopRated> getTvTopRated(
            @Query(Api.KEY) String apiKey,
            @Query(Api.PAGE) int page);

    @GET(Api.TV + Api.ID_PARAM)
    Call<TvDetails> getTvDetails(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.TV + Api.ID_PARAM + Api.IMAGES)
    Call<Images> getTvImages(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.TV + Api.ID_PARAM + Api.VIDEOS)
    Call<Videos> getTvVideos(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.TV + Api.ID_PARAM + Api.REVIEWS)
    Call<Reviews> getTvReviews(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.TV + Api.ID_PARAM + Api.CREDITS)
    Call<Credits> getTvCredits(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    @GET(Api.TV + Api.ID_PARAM + Api.SIMILAR)
    Call<TvSimilar> getTvSimilar(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    /**
     * API People calls
     */
    @GET(Api.PEOPLE + Api.POPULAR)
    Call<PeoplePopular> getPeoplePopular(
            @Query(Api.KEY) String apiKey,
            @Query(Api.PAGE) int page);

    @GET(Api.PEOPLE + Api.ID_PARAM)
    Call<PeopleDetails> getPeopleDetails(
            @Path(Api.ID) int id,
            @Query(Api.KEY) String apiKey);

    /**
     * API Genre calls
     */
    @GET(Api.GENRE_MOVIES + Api.LIST)
    Call<Genres> getGenresMovies(
            @Query(Api.KEY) String apiKey);

    @GET(Api.GENRE_TV + Api.LIST)
    Call<Genres> getGenresTv(
            @Query(Api.KEY) String apiKey);

    abstract class Api {
        private static final String MOVIES = "3/movie/";
        private static final String TV = "3/tv/";
        private static final String PEOPLE = "3/person/";
        private static final String GENRE_MOVIES = "3/genre/movies/";
        private static final String GENRE_TV = "3/genre/tv/";
        private static final String POPULAR = "popular";
        private static final String TOP_RATED = "top_rated";
        private static final String VIDEOS = "videos";
        private static final String REVIEWS = "reviews";
        private static final String CREDITS = "credits";
        private static final String SIMILAR = "similar";
        private static final String IMAGES = "images";
        private static final String LIST = "list";
        private static final String KEY = "api_key";
        private static final String PAGE = "page";
        private static final String ID = "id";
        private static final String ID_PARAM = "{id}/";
    }
}
