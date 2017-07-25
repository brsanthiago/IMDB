package br.com.brsantiago.imdb.network;

import br.com.brsantiago.imdb.model.ImdbMovie;
import br.com.brsantiago.imdb.model.Search;
import br.com.brsantiago.imdb.model.Sugestion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bruno on 23/07/17.
 */

public interface MovieService {
    @GET("/")
    Call<ImdbMovie> findMovie(@Query("t") String title);
    @GET("/")
    Call<ImdbMovie> findMovieById(@Query("i") String id);
    @GET("/")
    Call<Search> findMovies(@Query("s") String title);
}
