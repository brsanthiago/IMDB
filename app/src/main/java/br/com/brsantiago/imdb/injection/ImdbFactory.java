package br.com.brsantiago.imdb.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import br.com.brsantiago.imdb.ImdbApp;

/**
 * Created by bruno on 23/07/17.
 */

public class ImdbFactory extends ViewModelProvider.NewInstanceFactory {

    private ImdbApp app;

    public ImdbFactory(ImdbApp app) {
        this.app = app;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        T t = super.create(modelClass);
        if (t instanceof ImdbComponent.Injectable) {
            ((ImdbComponent.Injectable) t).inject(app.getImdbComponent());
        }
        return t;
    }
}
