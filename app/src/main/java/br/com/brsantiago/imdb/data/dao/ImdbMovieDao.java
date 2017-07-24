package br.com.brsantiago.imdb.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;
import br.com.brsantiago.imdb.model.ImdbMovie;

/**
 * Created by bruno on 23/07/17.
 */

@Dao
public interface ImdbMovieDao {

    @Query("SELECT * from movie order by title, year asc")
    public LiveData<List<ImdbMovie>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ImdbMovie> movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ImdbMovie movie);


    @Delete
    void delete(ImdbMovie imdbMovie);

    @Query("SELECT * from movie where id = :movieID")
    LiveData<ImdbMovie> findById(String movieID);
}
