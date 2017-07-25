package br.com.brsantiago.imdb.view.model;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
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

public class MovieViewModel extends ViewModel implements ImdbComponent.Injectable {
    private String movieId = "";
    private ImdbMovie movie;
    private Context context;

    @Inject
    MovieRepository movieRepository;
    @Inject
    MovieService movieService;

    public LiveData<ImdbMovie> movieLiveData = new MutableLiveData<>();

    @Override
    public void inject(ImdbComponent component) {
        component.inject(this);
        movieLiveData = movieRepository.findById(getMovieId());
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void findMovie() {
        movieService.findMovieById(movie.getImdbId()).enqueue(new Callback<ImdbMovie>() {
            @Override
            public void onResponse(Call<ImdbMovie> call, final Response<ImdbMovie> response) {
                if (response.isSuccessful() && response.body().isResponse()) {
                    setMovie(response.body());
                    getMovieLiveData();
                }
            }

            @Override
            public void onFailure(Call<ImdbMovie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void save(final ImdbMovie movie) {
        movie.setSaved(true);
        movieRepository.insert(movie).observeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(AddMovieViewModel.class.getSimpleName(), "onComplete movie saved");
                        getMovieLiveData();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(AddMovieViewModel.class.getSimpleName(), "onComplete onError saved");
                    }
                });
    }
    public void delete(final ImdbMovie movie) {
        movie.setSaved(false);
        movieRepository.delete(movie).observeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(AddMovieViewModel.class.getSimpleName(), "onComplete movie deleted");
                        ((Activity)context).finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(AddMovieViewModel.class.getSimpleName(), "onComplete onError deleted");
                    }
                });
    }

    public LiveData<ImdbMovie> getMovieLiveData() {
        return movieLiveData;
    }

    public ImdbMovie getMovie() {
        return movie;
    }

    public void setMovie(ImdbMovie movie) {
        this.movie = movie;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
