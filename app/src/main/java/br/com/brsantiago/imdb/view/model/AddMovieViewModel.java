package br.com.brsantiago.imdb.view.model;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import java.util.List;
import javax.inject.Inject;
import br.com.brsantiago.imdb.data.repository.MovieRepository;
import br.com.brsantiago.imdb.data.repository.SugestionRepository;
import br.com.brsantiago.imdb.injection.ImdbComponent;
import br.com.brsantiago.imdb.model.ImdbMovie;
import br.com.brsantiago.imdb.model.Search;
import br.com.brsantiago.imdb.model.Sugestion;
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

public class AddMovieViewModel extends ViewModel implements ImdbComponent.Injectable {
    private String movieName;
    private Context context;
    @Inject
    SugestionRepository sugestionRepository;
    @Inject
    MovieRepository movieRepository;
    @Inject
    MovieService movieService;

    public LiveData<List<Sugestion>> sugestions = new MutableLiveData<>();

    @Override
    public void inject(ImdbComponent component) {
        component.inject(this);
        sugestions = sugestionRepository.findAll();
    }
    public void setContet(final Context ctx) {
        this.context = ctx;
    }

    public LiveData<List<Sugestion>> findAll() {
        return sugestions;
    }

    public void findMovies() {
        movieService.findMovies(getMovieName()).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, final Response<Search> response) {
                if (response.isSuccessful() && response.body().isResponse()) {
                    sugestionRepository.insertAll(response.body().getSugestions())
                            .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onComplete() {
                            findAll();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void deleteAll() {
        sugestionRepository.delete(sugestions.getValue())
                .observeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        ((Activity)context).finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

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
