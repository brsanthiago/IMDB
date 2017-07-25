package br.com.brsantiago.imdb.injection;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.squareup.picasso.Picasso;
import javax.inject.Singleton;
import br.com.brsantiago.imdb.ImdbApp;
import br.com.brsantiago.imdb.data.AppDataBase;
import br.com.brsantiago.imdb.data.repository.MovieRepository;
import br.com.brsantiago.imdb.data.repository.SugestionRepository;
import br.com.brsantiago.imdb.network.ImdbApi;
import br.com.brsantiago.imdb.network.MovieService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruno on 23/07/17.
 */
@Module
public class ImdbModule {
    private ImdbApp app;

    public ImdbModule(ImdbApp app) {
        this.app = app;
    }
    @Provides
    public Context applicationContext() {
        return app;
    }

    @Provides
    @Singleton
    public MovieRepository movieRepository(AppDataBase appDataBase) {
        return new MovieRepository(appDataBase);
    }
    @Provides
    @Singleton
    public SugestionRepository sugestionRepository(AppDataBase appDataBase) {
        return new SugestionRepository(appDataBase);
    }

    @Provides
    @Singleton
    public AppDataBase appDataBase(Context ctx) {
        return Room.databaseBuilder(ctx.getApplicationContext(), AppDataBase.class, "movie-db")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public Picasso picasso() {
        return new Picasso.Builder(applicationContext()).build();
    }

    @Provides
    @Singleton
    public ImdbApi imdbApi() {
        return new ImdbApi();
    }

    @Provides
    @Singleton
    public MovieService movieService() {
        return imdbApi().provideApi().create(MovieService.class);
    }
}

