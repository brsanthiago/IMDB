package br.com.brsantiago.imdb.data.repository;

import android.arch.lifecycle.LiveData;
import java.util.List;
import javax.inject.Inject;
import br.com.brsantiago.imdb.data.AppDataBase;
import br.com.brsantiago.imdb.model.Sugestion;
import io.reactivex.Completable;
import io.reactivex.functions.Action;

/**
 * Created by bruno on 23/07/17.
 */

public class SugestionRepository implements IMovieRepository<Sugestion> {

    @Inject
    AppDataBase appDataBase;

    public SugestionRepository(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
    }

    @Override
    public Completable insert(final Sugestion sugestion) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.sugestionDao().insert(sugestion);
            }
        });
    }
    public Completable insertAll(final List<Sugestion> sugestion) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.sugestionDao().insertAll(sugestion);
            }
        });
    }

    @Override
    public LiveData<List<Sugestion>> findAll() {
        return appDataBase.sugestionDao().findAll();
    }

    @Override
    public Completable delete(final Sugestion sugestion) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.sugestionDao().delete(sugestion);
            }
        });
    }
    public Completable delete(final List<Sugestion> sugestions) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDataBase.sugestionDao().delete(sugestions);
            }
        });
    }
}
