package br.com.brsantiago.imdb.view;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.List;

import br.com.brsantiago.imdb.ImdbApp;
import br.com.brsantiago.imdb.R;
import br.com.brsantiago.imdb.injection.ImdbFactory;
import br.com.brsantiago.imdb.model.Sugestion;
import br.com.brsantiago.imdb.view.adapter.SugestionMovieAdapter;
import br.com.brsantiago.imdb.view.model.AddMovieViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMovieActivity extends BaseActivity {
    private SugestionMovieAdapter sugestionMovieAdapter;
    private AddMovieViewModel addMovieViewModel;
    private AlertDialog dialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_movies)
    RecyclerView recyclerView;
    @BindView(R.id.loading_view)
    FrameLayout loadginView;
    @BindView(R.id.view_movie)
    ConstraintLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        ButterKnife.bind(this);

        ImdbApp app = (ImdbApp) getApplication();

        sugestionMovieAdapter = new SugestionMovieAdapter(this);
        bindRecycle();

        addMovieViewModel = ViewModelProviders.of(this, new ImdbFactory(app)).get(AddMovieViewModel.class);
        addMovieViewModel.setContet(this);
        bindSugestions();
        addMovie();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMovieViewModel.deleteAll();
            }
        });
    }

    private void bindSugestions() {
        addMovieViewModel.findAll().observe(this, new Observer<List<Sugestion>>() {
            @Override
            public void onChanged(@Nullable List<Sugestion> sugestions) {
                if (sugestions != null && !sugestions.isEmpty()) {
                    sugestionMovieAdapter.setSugestions(sugestions);
                    showContent();
                }
            }
        });
    }

    private void bindRecycle() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(sugestionMovieAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void addMovie() {
        View view = View.inflate(this, R.layout.popup_add_movie, null);
        final AppCompatEditText edMovie = (AppCompatEditText) view.findViewById(R.id.ed_movie);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(view)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addMovieViewModel.deleteAll();
                    }
                })
                .setPositiveButton(R.string.find_movie, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog = builder.create();
        dialog.show();
        final Button btnFind =  dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMovieViewModel.findMovies();
                showLoading();
                dialog.dismiss();
            }
        });

        edMovie.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        final String movie = s.toString().trim();
                        btnFind.setEnabled(movie.length() > 0);
                        addMovieViewModel.setMovieName(movie);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });
    }

    private void showLoading() {
        contentView.setVisibility(View.GONE);
        loadginView.setVisibility(View.VISIBLE);
    }
    private void showContent() {
        contentView.setVisibility(View.VISIBLE);
        loadginView.setVisibility(View.GONE);
    }
    private void showNotFound() {
        contentView.setVisibility(View.GONE);
        loadginView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        addMovieViewModel.deleteAll();
    }
}
