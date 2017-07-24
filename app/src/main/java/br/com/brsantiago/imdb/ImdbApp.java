package br.com.brsantiago.imdb;

import android.app.Application;

import br.com.brsantiago.imdb.injection.DaggerImdbComponent;
import br.com.brsantiago.imdb.injection.ImdbComponent;
import br.com.brsantiago.imdb.injection.ImdbModule;

/**
 * Created by bruno on 23/07/17.
 */

public class ImdbApp extends Application {

    private ImdbComponent imdbComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        imdbComponent = DaggerImdbComponent.builder().imdbModule(new ImdbModule(this)).build();
    }

    public ImdbComponent getImdbComponent() {
        return imdbComponent;
    }
}
