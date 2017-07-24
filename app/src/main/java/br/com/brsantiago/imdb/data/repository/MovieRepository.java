package br.com.brsantiago.imdb.data.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import br.com.brsantiago.imdb.data.AppDataBase;
import br.com.brsantiago.imdb.model.ImdbMovie;
import io.reactivex.Completable;
import io.reactivex.functions.Action;

/**
 * Created by bruno on 23/07/17.
 */

public class MovieRepository implements IMovieRepository {

    @Inject
    AppDataBase appDataBase;

    public MovieRepository(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
    }

    @Override
    public Completable insert(final ImdbMovie movie) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.movieDao().insert(movie);
            }
        });
    }

    @Override
    public LiveData<List<ImdbMovie>> getMovies() {
        return appDataBase.movieDao().findAll();
    }

    @Override
    public Completable delete(final ImdbMovie movie) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.movieDao().delete(movie);
            }
        });
    }
}
