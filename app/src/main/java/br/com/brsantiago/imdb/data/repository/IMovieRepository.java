package br.com.brsantiago.imdb.data.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import br.com.brsantiago.imdb.model.ImdbMovie;
import io.reactivex.Completable;

/**
 * Created by bruno on 23/07/17.
 */

public interface IMovieRepository {

    Completable insert(ImdbMovie movie);

    LiveData<List<ImdbMovie>> getMovies();

    Completable delete(ImdbMovie movie);
}
