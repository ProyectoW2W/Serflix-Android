package com.example.dam.serflix.Controller.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam.serflix.Controller.MovieCard;
import com.example.dam.serflix.Controller.Repository.MovieCardRepository;
import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RecommendationActivity extends Activity {
    MovieCard movieCard, movieCard1;

    private ArrayList<MovieCard> movieCards;
    private ArrayAdapter<MovieCard> arrayAdapter;
    private int i;
    final MovieCardRepository movies = new MovieCardRepository();

    @InjectView(R.id.frame)
    SwipeFlingAdapterView flingAdapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        ButterKnife.inject(this);

        movies.loadMovies();

        final CatalogAdapter catalogAdapter = new CatalogAdapter(this, movies);

        flingAdapterView.setAdapter(catalogAdapter);
        flingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                movies.deleteMovieFromIndex(0);
                catalogAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                makeToast(RecommendationActivity.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(RecommendationActivity.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                movies.addMovie(movieCard1);
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingAdapterView.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        // Optionally add an OnItemClickListener
        flingAdapterView.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(RecommendationActivity.this, "Clicked!");
            }
        });
    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.right)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingAdapterView.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {
        flingAdapterView.getTopCardListener().selectLeft();
    }



    public class CatalogAdapter extends BaseAdapter {

        private Context context;
        private MovieCardRepository catalog;

        public CatalogAdapter(Context context, MovieCardRepository catalog) {
            this.context = context;
            this.catalog = catalog;
        }

        @Override
        public int getCount() {
            return catalog.getNumOfMovies();
        }

        @Override
        public Object getItem(int position) {
            return catalog.getMovieCardFromIndex(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder {

            public TextView title;
            public TextView cast;
            public TextView tags;
            public ImageView poster;
            public TextView description;
            public TextView year;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View myView = convertView;
            if (myView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(ContextThemeWrapper.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.movie_card, parent, false);
                ViewHolder holder = new ViewHolder();
                holder.title = (TextView) myView.findViewById(R.id.title);
                holder.cast = (TextView) myView.findViewById(R.id.cast);
                holder.tags = (TextView) myView.findViewById(R.id.tags);
                holder.poster = (ImageView) myView.findViewById(R.id.poster);
                holder.description = (TextView) myView.findViewById(R.id.description);
                holder.year = (TextView) myView.findViewById(R.id.year);
                myView.setTag(holder);
            }
            CatalogAdapter.ViewHolder holder = (CatalogAdapter.ViewHolder) myView.getTag();
            Movie movie = catalog.getMovieFromMovieCard(position, movies.getMovieCardFromIndex(position));
            String title = movie.getTitle();
            holder.title.setText(title);
            String cast = movie.getCast();
            holder.cast.setText(cast);
            String tags = movie.getTags();
            holder.tags.setText(tags);
            if (!catalog.getMovieFromMovieCard(position, movies.getMovieCardFromIndex(position)).getPoster().equals("")) {
                Picasso.with(context).load(movie.getPoster()).placeholder(R.drawable.add).into(holder.poster);
            }
            String description = movie.getDescription();
            holder.description.setText(description);
            String year = movie.getYear();
            holder.year.setText(year);
            return myView;
        }
    }

}


