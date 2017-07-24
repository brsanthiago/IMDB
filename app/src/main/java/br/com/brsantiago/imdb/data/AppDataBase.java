package br.com.brsantiago.imdb.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import br.com.brsantiago.imdb.data.dao.ImdbMovieDao;
import br.com.brsantiago.imdb.model.ImdbMovie;

/**
 * Created by bruno on 23/07/17.
 */
@Database(entities = {ImdbMovie.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    static final String DATABASE_NAME = "imdb-movie.db";

    public abstract ImdbMovieDao movieDao();

}
