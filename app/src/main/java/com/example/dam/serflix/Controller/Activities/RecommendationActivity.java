package com.example.dam.serflix.Controller.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dam.serflix.Controller.Adapter.CardAdapter;
import com.example.dam.serflix.Controller.Managers.RequestCallback;
import com.example.dam.serflix.Controller.Managers.RequestManager;
import com.example.dam.serflix.Model.Movie;
import com.example.dam.serflix.Model.MovieRecommendation;
import com.example.dam.serflix.Model.Request;
import com.example.dam.serflix.Model.RequestDTO;
import com.example.dam.serflix.Model.enumeration.RecomendationResult;
import com.example.dam.serflix.R;
import com.huxq17.swipecardsview.SwipeCardsView;

import java.util.ArrayList;
import java.util.List;


public class RecommendationActivity extends AppCompatActivity implements RequestCallback{

    private SwipeCardsView swipeCardsView;
    private List<MovieRecommendation> movieList = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private int curIndex;
    private Context context;
    private long requestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        swipeCardsView = (SwipeCardsView)findViewById(R.id.swipeCardsView);
        swipeCardsView.retainLastCard(false);
        swipeCardsView.enableSwipe(true);
        context = this.getApplicationContext();
        requestId = getIntent().getLongExtra("requestId", 0);
        //getData();

        swipeCardsView.setCardsSlideListener(new SwipeCardsView.CardsSlideListener() {
            @Override
            //METODO PARA CUANDO SE ESTA MOSTRANDO LA TARJETA.
            public void onShow(int index) {
                curIndex = index;
                Log.d("Sergio", "Tarjeta "+curIndex);
            }

            @Override
            //METODO PARA CUANDO SE DESLIZA LA TARJETA.
            public void onCardVanish(int index, SwipeCardsView.SlideType type) {
                switch (type) {
                    case LEFT:
                        Toast.makeText(context, "RECHAZADA", Toast.LENGTH_SHORT).show();
                        //PUT A BACKEND CAMBIANDO EL ESTADO DE LA MOVIERECOMENDATION (RECHAZADA)
                        movieList.get(index).setRecomendationResult(RecomendationResult.REJECTED);
                        break;
                    case RIGHT:
                        Toast.makeText(context, "ACEPTADA", Toast.LENGTH_SHORT).show();
                        //PUT A BACKEND CAMBIANDO EL ESTADO DE LA MOVIERECOMENDATION (ACEPTADA)
                        movieList.get(index).setRecomendationResult(RecomendationResult.ACCEPTED);
                        Intent intent = new Intent(RecommendationActivity.this, ResultActivity.class);
                        intent.putExtra("poster", movieList.get(index).getMovieDTO().getPoster());
                        intent.putExtra("title", movieList.get(index).getMovieDTO().getTitle());
                        startActivity(intent);
                        break;
                }
            }

            @Override
            //METODO PARA CUANDO SE HACE CLICK EN LA TARJETA
            public void onItemClick(View cardImageView, int index) {
                Log.d("Sergio","Click en card "+index);

            }
        });

        RequestManager.getInstance().getRecomendations(RecommendationActivity.this, requestId);
    }



    private void getData() {

        Movie m1 = new Movie("Fight Club","Brad Pitt","Drama","https://image.tmdb.org/t/p/w1280/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg","A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.","1999-10-15"); movies.add(m1);
        Movie m2 = new Movie("The Poseidon Adventure","Gene Hackman","Action, Adventure","https://image.tmdb.org/t/p/w1280/3Ypk0OLrECSp7tqQFLMppGBrHfo.jpg","The Poseidon Adventure was one of the first Catastrophe films and began the Disaster Film genre. Director Neame tells the story of a group of people that must fight for their lives aboard a sinking ship. Based on the novel by Paul Gallico.","1972-12-01"); movies.add(m2);
        Movie m3 = new Movie("Pane e Tulipani","Licia Maglietta","Drama, Comedy, Romance","https://image.tmdb.org/t/p/w1280/bVsJsE6fSzauhFZDuhNJ9SHs10f.jpg","An endearing light comedy about a woman who spontaneously becomes a resident of Venice after her family left her begin. While enjoying the wonderful people she meets she achieves a new life and the first time independent of her family.","2000-03-03"); movies.add(m3);
        Movie m4 = new Movie("Dogville","Nicole Kidman","Crime, Drama, Thriller","https://image.tmdb.org/t/p/w1280/g1xLrof2RVgtHBB4fLvR5Xr8l5x.jpg","A barren soundstage is stylishly utilized to create a minimalist small-town setting in which a mysterious woman named Grace hides from the criminals who pursue her. The town is two-faced and offers to harbor Grace as long as she can make it worth their effort, so Grace works hard under the employ of various townspeople to win their favor. Tensions flare, however, and Grace's status as a helpless outsider provokes vicious contempt and abuse from the citizens of Dogville.","2003-05-19"); movies.add(m4);
        Movie m5 = new Movie("Kukushka","Ville Haapasalo","Drama, History, Romance","https://image.tmdb.org/t/p/w1280/A2S4Ut2mDmedw7JQHxfCsnxCprV.jpg","September of 1944, a few days before Finland went out of the Second World War. A chained to a rock Finnish sniper-kamikadze Veikko managed to set himself free. Ivan, a captain of the Soviet Army, arrested by the Front Secret Police 'Smersh', has a narrow escape. They are soldiers of the two enemy armies. A Lapp woman Anni gives a shelter to both of them at her farm. For Anni they are not enemies, but just men.","2002-01-01"); movies.add(m5);
        Movie m6 = new Movie("Absolut","Iréne Godel","Thriller","https://image.tmdb.org/t/p/w1280/6YemisOilgHbBp6UtgoONHg8eJk.jpg","Two guys against globalization want to plant a virus in the network of a finance corporation. On the day of the attack Alex has an accident and cannot remember anything.","2005-04-20"); movies.add(m6);
        Movie m7 = new Movie("Spider-Man","Tobey Maguire","Action, Fantasy","https://image.tmdb.org/t/p/w1280/rZd0y1X1Gw4t5B3f01Qzj8DYY66.jpg","After being bitten by a genetically altered spider, nerdy high school student Peter Parker is endowed with amazing powers.","2002-05-01"); movies.add(m7);
        Movie m8 = new Movie("Spider-Man 2","Tobey Maguire","Action, Fantasy","https://image.tmdb.org/t/p/w1280/qtBFrsEQ4oXW8sKvRxkKnYuPLg.jpg","Peter Parker is going through a major identity crisis. Burned out from being Spider-Man, he decides to shelve his superhero alter ego, which leaves the city suffering in the wake of carnage left by the evil Doc Ock. In the meantime, Parker still can't act on his feelings for Mary Jane Watson, a girl he's loved since childhood.","2004-06-25"); movies.add(m8);
        Movie m9 = new Movie("Spider-Man 3","Tobey Maguire","Action, Fantasy","https://image.tmdb.org/t/p/w1280/uC2pAMjb32NIgQ1GdC1Bl6LZJc2.jpg","The seemingly invincible Spider-Man goes up against an all-new crop of villain – including the shape-shifting Sandman. While Spider-Man’s superpowers are altered by an alien organism, his alter ego, Peter Parker, deals with nemesis Eddie Brock and also gets caught up in a love triangle.","2007-05-01"); movies.add(m9);
        Movie m10 = new Movie("Secret Beyond the Door","Joan Bennett","Crime, Drama, Thriller","https://image.tmdb.org/t/p/w1280/f2CqQR8DF2I3DvmJFp5lRzRKD30.jpg","Fritz Lang’s psycho thriller tells the story of a woman who marries a stranger with a deadly hobby and through their love he attempts to fight off his obsessive-compulsive actions.","1948-01-01"); movies.add(m10);

        for (Movie m:movies) {
            MovieRecommendation mr = new MovieRecommendation();
            mr.setMovieDTO(m);
            movieList.add(mr);
        }
        List<MovieRecommendation> recommendations = RequestManager.getInstance().getMovieRecommendations();

        //movieList.add(new Movie("Fight Club","Brad Pitt","Drama","https://image.tmdb.org/t/p/w1280/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg","A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.","1999-10-15"));
        //movieList.add(new Movie("The Poseidon Adventure","Gene Hackman","Action, Adventure","https://image.tmdb.org/t/p/w1280/3Ypk0OLrECSp7tqQFLMppGBrHfo.jpg","The Poseidon Adventure was one of the first Catastrophe films and began the Disaster Film genre. Director Neame tells the story of a group of people that must fight for their lives aboard a sinking ship. Based on the novel by Paul Gallico.","1972-12-01"));
        //movieList.add(new Movie("Pane e Tulipani","Licia Maglietta","Drama, Comedy, Romance","https://image.tmdb.org/t/p/w1280/bVsJsE6fSzauhFZDuhNJ9SHs10f.jpg","An endearing light comedy about a woman who spontaneously becomes a resident of Venice after her family left her begin. While enjoying the wonderful people she meets she achieves a new life and the first time independent of her family.","2000-03-03"));
        //movieList.add(new Movie("Dogville","Nicole Kidman","Crime, Drama, Thriller","https://image.tmdb.org/t/p/w1280/g1xLrof2RVgtHBB4fLvR5Xr8l5x.jpg","A barren soundstage is stylishly utilized to create a minimalist small-town setting in which a mysterious woman named Grace hides from the criminals who pursue her. The town is two-faced and offers to harbor Grace as long as she can make it worth their effort, so Grace works hard under the employ of various townspeople to win their favor. Tensions flare, however, and Grace's status as a helpless outsider provokes vicious contempt and abuse from the citizens of Dogville.","2003-05-19"));
        //movieList.add(new Movie("Kukushka","Ville Haapasalo","Drama, History, Romance","https://image.tmdb.org/t/p/w1280/A2S4Ut2mDmedw7JQHxfCsnxCprV.jpg","September of 1944, a few days before Finland went out of the Second World War. A chained to a rock Finnish sniper-kamikadze Veikko managed to set himself free. Ivan, a captain of the Soviet Army, arrested by the Front Secret Police 'Smersh', has a narrow escape. They are soldiers of the two enemy armies. A Lapp woman Anni gives a shelter to both of them at her farm. For Anni they are not enemies, but just men.","2002-01-01"));
        //movieList.add(new Movie("Absolut","Iréne Godel","Thriller","https://image.tmdb.org/t/p/w1280/6YemisOilgHbBp6UtgoONHg8eJk.jpg","Two guys against globalization want to plant a virus in the network of a finance corporation. On the day of the attack Alex has an accident and cannot remember anything.","2005-04-20"));
        //movieList.add(new Movie("Spider-Man","Tobey Maguire","Action, Fantasy","https://image.tmdb.org/t/p/w1280/rZd0y1X1Gw4t5B3f01Qzj8DYY66.jpg","After being bitten by a genetically altered spider, nerdy high school student Peter Parker is endowed with amazing powers.","2002-05-01"));
        //movieList.add(new Movie("Spider-Man 2","Tobey Maguire","Action, Fantasy","https://image.tmdb.org/t/p/w1280/qtBFrsEQ4oXW8sKvRxkKnYuPLg.jpg","Peter Parker is going through a major identity crisis. Burned out from being Spider-Man, he decides to shelve his superhero alter ego, which leaves the city suffering in the wake of carnage left by the evil Doc Ock. In the meantime, Parker still can't act on his feelings for Mary Jane Watson, a girl he's loved since childhood.","2004-06-25"));
        //movieList.add(new Movie("Spider-Man 3","Tobey Maguire","Action, Fantasy","https://image.tmdb.org/t/p/w1280/uC2pAMjb32NIgQ1GdC1Bl6LZJc2.jpg","The seemingly invincible Spider-Man goes up against an all-new crop of villain – including the shape-shifting Sandman. While Spider-Man’s superpowers are altered by an alien organism, his alter ego, Peter Parker, deals with nemesis Eddie Brock and also gets caught up in a love triangle.","2007-05-01"));
        //movieList.add(new Movie("Secret Beyond the Door","Joan Bennett","Crime, Drama, Thriller","https://image.tmdb.org/t/p/w1280/f2CqQR8DF2I3DvmJFp5lRzRKD30.jpg","Fritz Lang’s psycho thriller tells the story of a woman who marries a stranger with a deadly hobby and through their love he attempts to fight off his obsessive-compulsive actions.","1948-01-01"));

        CardAdapter cardAdapter = new CardAdapter(recommendations, this);
        swipeCardsView.setAdapter(cardAdapter);

    }

    @Override
    public void onSuccess(List<Request> requestsList) {

    }

    @Override
    public void onSuccess(RequestDTO request) {

    }

    @Override
    public void onSuccessMR(List<MovieRecommendation> movieRecommendations) {
        Log.d("fernando","successMr");
        getData();
    }

    @Override
    public void onFailure(Throwable t) {

    }
}


