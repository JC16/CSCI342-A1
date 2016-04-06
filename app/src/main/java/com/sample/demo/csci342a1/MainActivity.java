package com.sample.demo.csci342a1;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TileView.TileViewInterface, GameInterface  {

    private GameModel gm;
    private ArrayList<TileView> tileView = new ArrayList<>();
    private final int numOfImg =12;
    private TileView Tview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable ImgDrawable[] = {getResources().getDrawable(R.drawable.lake),
                getResources().getDrawable(R.drawable.cathedral),
                getResources().getDrawable(R.drawable.baldhill),
                getResources().getDrawable(R.drawable.android),
                getResources().getDrawable(R.drawable.ios),
                getResources().getDrawable(R.drawable.androids)};

        gm = new GameModel(numOfImg, ImgDrawable);



        for(int i=0; i<numOfImg; i++)
        {
            try {
                int id = R.id.class.getField("View"+i).getInt(0);
                Tview = (TileView)findViewById(id);
                tileView.add(Tview);

                tileView.get(i).revailImage(gm.TileList.get(i).photo);
                tileView.get(i).setTileIndex(i);
                tileView.get(i).setInterface(this);
                tileView.get(i).coverImage();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }


        Log.e("MainActivity", "Testing");
        Log.e("MainActivity", gm.description());
        Log.e("MainActivity", Integer.toString(tileView.size()));



    }

    @Override
    public void didSelectTile(TileView tileView) {
            int lastTap = tileView.getTileIndex();

            gm.pushTileIndex(lastTap);
            Log.e("MainActivity", Integer.toString(lastTap));

            didMatchTile(gm, gm.LastTapped, gm.SecondTapped);
            didFailToMatchTile(gm,gm.LastTapped,gm.SecondTapped);

    }

    @Override
    public void gameDidComplete(GameModel gameModel) {

    }

    @Override
    public void didMatchTile(GameModel gameModel, int tileIndex, int previousTileIndex) {

        final int TIndex = tileIndex;
        final int PIndex = previousTileIndex;
        final GameModel gm = gameModel;

        if(gameModel.TileList.get(tileIndex).ImgId == gameModel.TileList.get(previousTileIndex).ImgId)
        {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tileView.get(TIndex).hideTile();
                    tileView.get(PIndex).hideTile();

                }
            }, 100);

        }

    }

    @Override
    public void didFailToMatchTile(GameModel gameModel, int tileIndex, int previousTileIndex) {
        if(gameModel.TileList.get(tileIndex).ImgId != gameModel.TileList.get(previousTileIndex).ImgId)
        {
            final int TIndex = tileIndex;
            final int PIndex = previousTileIndex;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tileView.get(TIndex).coverImage();
                    tileView.get(PIndex).coverImage();
                }
            }, 100);

        }
    }

    @Override
    public void scoreDidUpdate(GameModel gameModel, int newScore) {

    }
}
