package br.com.brsantiago.imdb.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import org.w3c.dom.Text;

import java.util.List;

import br.com.brsantiago.imdb.ImdbApp;
import br.com.brsantiago.imdb.R;
import br.com.brsantiago.imdb.injection.ImdbFactory;
import br.com.brsantiago.imdb.model.ImdbMovie;
import br.com.brsantiago.imdb.view.adapter.MovieAdapter;
import br.com.brsantiago.imdb.view.model.MovieListViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private MovieAdapter movieAdapter;
    private MovieListViewModel listViewModel;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rv_movies)
    RecyclerView recyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private View positiveAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ImdbApp app = (ImdbApp) getApplication();

        movieAdapter = new MovieAdapter(this);
        bindRecycle();

        listViewModel = ViewModelProviders.of(this, new ImdbFactory(app)).get(MovieListViewModel.class);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMovie();
            }
        });
        loadMovies();
    }

    private void addMovie() {
        boolean wrapInScrollView = true;

        MaterialDialog dialog =new MaterialDialog.Builder(this)
                .customView(R.layout.activity_add_movie, wrapInScrollView)
                .negativeText(R.string.cancel)
                .positiveText(R.string.find_movie)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        listViewModel.save();
                    }
                })
                .show();

        positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        EditText edMovie = (EditText) dialog.getCustomView().findViewById(R.id.ed_movie);
        edMovie.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        final String movie = s.toString().trim();
                        positiveAction.setEnabled(movie.length() > 0);
                        listViewModel.setMovieName(movie);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });
    }

    private void loadMovies() {
        listViewModel.findAll().observe(this, new Observer<List<ImdbMovie>>() {
            @Override
            public void onChanged(@Nullable List<ImdbMovie> movies) {
                if (movies != null && !movies.isEmpty()) {
                    movieAdapter.setMovies(movies);
                }
            }
        });
    }

    private void bindRecycle() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(movieAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
