package br.com.brsantiago.imdb.view;

import android.os.Bundle;
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
import br.com.brsantiago.imdb.model.ImdbMovie;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        ((ImdbApp)getApplication()).getImdbComponent().inject(this);

        ImdbMovie movie = (ImdbMovie) getIntent().getExtras().getSerializable("movie");
        tvTitle.setText(movie.getTitle());
        picasso.load(movie.getPoster()).placeholder(R.drawable.ic_movie).into(ivPoster);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Integer id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
