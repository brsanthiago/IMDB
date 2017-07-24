package br.com.brsantiago.imdb.view.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import java.util.List;
import javax.inject.Inject;
import br.com.brsantiago.imdb.data.repository.MovieRepository;
import br.com.brsantiago.imdb.injection.ImdbComponent;
import br.com.brsantiago.imdb.model.ImdbMovie;
import br.com.brsantiago.imdb.network.MovieService;
import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bruno on 23/07/17.
 */

public class MovieListViewModel extends ViewModel implements ImdbComponent.Injectable {
    private String movieName;

    @Inject
    MovieRepository movieRepository;
    @Inject
    MovieService movieService;

    private LiveData<List<ImdbMovie>> movies = new MutableLiveData<>();

    @Override
    public void inject(ImdbComponent component) {
        component.inject(this);
        movies = movieRepository.getMovies();
    }

    public LiveData<List<ImdbMovie>> findAll() {
        return movies;
    }

    public void save() {
        movieService.findMovie(getMovieName()).enqueue(new Callback<ImdbMovie>() {
            @Override
            public void onResponse(Call<ImdbMovie> call, Response<ImdbMovie> response) {
                final ImdbMovie movie = response.body();
                if (response.isSuccessful() && movie.isResponse())
                movieRepository.insert(response.body()).observeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(MovieListViewModel.class.getSimpleName(), "onComplete movie saved");
                        setMovieName("");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(MovieListViewModel.class.getSimpleName(), "onComplete onError saved");
                    }
                });
            }

            @Override
            public void onFailure(Call<ImdbMovie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
