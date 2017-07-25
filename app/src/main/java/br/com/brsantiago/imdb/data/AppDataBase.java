package br.com.brsantiago.imdb.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import br.com.brsantiago.imdb.data.dao.ImdbMovieDao;
import br.com.brsantiago.imdb.data.dao.SugestionDao;
import br.com.brsantiago.imdb.model.ImdbMovie;
import br.com.brsantiago.imdb.model.Search;
import br.com.brsantiago.imdb.model.Sugestion;

/**
 * Created by bruno on 23/07/17.
 */
@Database(entities = {ImdbMovie.class, Sugestion.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    static final String DATABASE_NAME = "imdb-movie.db";

    public abstract ImdbMovieDao movieDao();
    public abstract SugestionDao sugestionDao();

}
