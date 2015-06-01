package com.example.pulkit.cardslibapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;
import retrofit.RestAdapter;


public class MainActivity extends AppCompatActivity {

    CardRecyclerView rvCardList;
    CardArrayRecyclerViewAdapter cardArrayRecyclerViewAdapter;
    ArrayList<Card> cards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        rvCardList = (CardRecyclerView) findViewById(R.id.rvCardList);
        cards = new ArrayList<>();
        cardArrayRecyclerViewAdapter = new CardArrayRecyclerViewAdapter(this, cards);
        rvCardList.setLayoutManager(new LinearLayoutManager(this));

        if (rvCardList != null) {
            rvCardList.setAdapter(cardArrayRecyclerViewAdapter);
        }

        new FetchImageTask().execute();

    }

    public class FetchImageTask extends AsyncTask<Void, Void, List<ViralImage>> {

        @Override
        protected List<ViralImage> doInBackground(Void... params) {
            ImageView imageView = new RestAdapter.Builder().setEndpoint("https://viralfacebookimages.p.mashape.com").build().create(ImageView.class);
            return imageView.getViralImages();
        }

        @Override
        protected void onPostExecute(List<ViralImage> viralImages) {
            super.onPostExecute(viralImages);
            List<Card> cardList = new ArrayList<>();
            for (ViralImage viralImage : viralImages ) {
                MaterialLargeImageCard materialLargeImageCard = MaterialLargeImageCard.with(getBaseContext()).setTextOverImage(viralImage.likes_count + "Likes").useDrawableUrl(viralImage.src_big).build();
                cardList.add(materialLargeImageCard);
            }
            cardArrayRecyclerViewAdapter.addAll(cardList);
        }
    }

}
