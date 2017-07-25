package br.com.brsantiago.imdb.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import br.com.brsantiago.imdb.R;
import br.com.brsantiago.imdb.model.ImdbMovie;
import br.com.brsantiago.imdb.model.Sugestion;
import br.com.brsantiago.imdb.view.MovieDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bruno on 23/07/17.
 */

public class SugestionMovieAdapter extends RecyclerView.Adapter<SugestionMovieAdapter.MovieHolder> {
    List<? extends Sugestion> sugestions;
    private Picasso picasso;
    private Context context;
    public SugestionMovieAdapter(final Context ctx) {
        this.context = ctx;
    }

    public void setSugestions(final List<? extends Sugestion> mSugestions) {
        if (sugestions == null) {
            sugestions = mSugestions;
            notifyItemRangeInserted(0, mSugestions.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return sugestions.size();
                }

                @Override
                public int getNewListSize() {
                    return mSugestions.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return sugestions.get(oldItemPosition).getImdbId() ==
                            mSugestions.get(newItemPosition).getImdbId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Sugestion movie = mSugestions.get(newItemPosition);
                    Sugestion old = mSugestions.get(oldItemPosition);
                    return movie.getImdbId() == old.getImdbId();
                }
            });
            sugestions = mSugestions;
            result.dispatchUpdatesTo(this);
        }
        notifyDataSetChanged();
    }

    public List<? extends Sugestion> getSugestions() {
        return sugestions;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        picasso = new Picasso.Builder(parent.getContext()).build();
        MovieHolder holder = new MovieHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.setSugestion(sugestions.get(position));
    }

    @Override
    public int getItemCount() {
        return sugestions == null ? 0 : sugestions.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_poster)
        ImageView ivPoster;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.card_view)
        CardView cardView;

        public MovieHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            cardView.setOnClickListener(this);
        }

        public void setSugestion(final Sugestion sugestion) {
            picasso.load(sugestion.getPoster())
                    .placeholder(R.drawable.ic_movie).into(ivPoster);
            tvTitle.setText(sugestion.getTitle());
        }
        private void gotToDetails() {
            final Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("movie", createMovie());
            context.startActivity(intent);
        }

        private ImdbMovie createMovie() {
            final Sugestion sugestion = sugestions.get(getAdapterPosition());
            ImdbMovie movie = new ImdbMovie();
            movie.setImdbId(sugestion.getImdbId());
            movie.setTitle(sugestion.getTitle());
            movie.setPoster(sugestion.getPoster());
            return movie;
        }

        @Override
        public void onClick(View v) {
            if (cardView.getId() == v.getId())
                gotToDetails();
        }
    }
}
