package br.com.brsantiago.imdb.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;
import br.com.brsantiago.imdb.model.Sugestion;

/**
 * Created by bruno on 24/07/17.
 */
@Dao
public interface SugestionDao {
    @Query("SELECT * from sugestion order by title, year asc")
    LiveData<List<Sugestion>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Sugestion sugestion);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Sugestion> sugestions);

    @Delete
    void delete(Sugestion sugestions);
    @Delete
    void delete(List<Sugestion> sugestions);
}
