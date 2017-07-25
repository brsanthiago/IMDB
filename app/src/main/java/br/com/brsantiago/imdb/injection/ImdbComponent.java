package br.com.brsantiago.imdb.injection;

import javax.inject.Singleton;

import br.com.brsantiago.imdb.view.MovieDetailsActivity;
import br.com.brsantiago.imdb.view.model.AddMovieViewModel;
import br.com.brsantiago.imdb.view.model.MovieListViewModel;
import br.com.brsantiago.imdb.view.model.MovieViewModel;
import dagger.Component;

/**
 * Created by bruno on 23/07/17.
 */

@Singleton
@Component(modules = {ImdbModule.class})
public interface ImdbComponent {

    void inject(MovieDetailsActivity activity);
    void inject(MovieListViewModel viewModel);
    void inject(MovieViewModel viewModel);
    void inject(AddMovieViewModel viewModel);

    interface Injectable {
        void inject(ImdbComponent component);
    }
}
