package br.com.brsantiago.imdb.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.brsantiago.imdb.ImdbApp;
import br.com.brsantiago.imdb.R;
import br.com.brsantiago.imdb.injection.ImdbComponent;
import br.com.brsantiago.imdb.injection.ImdbFactory;
import br.com.brsantiago.imdb.model.ImdbMovie;
import br.com.brsantiago.imdb.view.model.MovieViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailsActivity extends BaseActivity implements ImdbComponent.Injectable{
    private MovieViewModel movieViewModel;
    private ImdbMovie movie;
    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fab)
    FloatingActionButton fabSave;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        ImdbApp app = (ImdbApp) getApplication();

        picasso = new Picasso.Builder(this).build();

        movieViewModel = ViewModelProviders.of(this, new ImdbFactory(app)).get(MovieViewModel.class);
        movie = (ImdbMovie) getIntent().getExtras().getSerializable("movie");

        movieViewModel.setContext(this);
        movieViewModel.setMovie(movie);
        movieViewModel.findMovie();
        bindMovie();
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieViewModel.findMovie();
        bindMovie();
    }

    @OnClick(R.id.fab)
    void saveMovie() {
        final boolean isSaved = movieViewModel.getMovieLiveData().getValue() != null ? movieViewModel.getMovieLiveData().getValue().isSaved() : movieViewModel.getMovie().isSaved();
        if (!isSaved) {
            movieViewModel.save(movieViewModel.getMovie());
        } else {
            movieViewModel.delete(movieViewModel.getMovie());
        }
    }

    private void bindMovie() {
        movieViewModel.getMovieLiveData().observe(this, new Observer<ImdbMovie>() {
            @Override
            public void onChanged(@Nullable ImdbMovie imdbMovie) {
                if (imdbMovie != null) {
                    startMovie(imdbMovie);
                } else {
                    startMovie(movieViewModel.getMovie());
                }
            }
        });
    }

    private void startMovie(final ImdbMovie movie) {
        tvTitle.setText(movie.getTitle());
        picasso.load(movie.getPoster()).placeholder(R.drawable.ic_movie).into(ivPoster);
        fabSave.setImageResource(movie.isSaved() ? R.drawable.ic_close : R.drawable.ic_add_white);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Integer id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void inject(ImdbComponent component) {
        component.inject(this);
    }
}
