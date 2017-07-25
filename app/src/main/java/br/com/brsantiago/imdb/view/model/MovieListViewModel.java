package br.com.brsantiago.imdb.view.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import javax.inject.Inject;
import br.com.brsantiago.imdb.data.repository.MovieRepository;
import br.com.brsantiago.imdb.injection.ImdbComponent;
import br.com.brsantiago.imdb.model.ImdbMovie;

/**
 * Created by bruno on 23/07/17.
 */

public class MovieListViewModel extends ViewModel implements ImdbComponent.Injectable {

    @Inject
    MovieRepository movieRepository;

    private LiveData<List<ImdbMovie>> movies = new MutableLiveData<>();

    @Override
    public void inject(ImdbComponent component) {
        component.inject(this);
        movies = movieRepository.findAll();
    }

    public LiveData<List<ImdbMovie>> findAll() {
        return movies;
    }

}
