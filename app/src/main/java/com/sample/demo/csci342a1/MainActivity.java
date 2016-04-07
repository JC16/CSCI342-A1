package com.sample.demo.csci342a1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TileView.TileViewInterface, GameInterface  {

    private GameModel gm;
    private ArrayList<TileView> tileView = new ArrayList<>();
    private final int numOfImg =12;
    private TileView Tview;
    private ArrayList<Drawable> ImgDrawable = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImgDrawable.add(getResources().getDrawable(R.drawable.lake));
        ImgDrawable.add(getResources().getDrawable(R.drawable.cathedral));
        ImgDrawable.add(getResources().getDrawable(R.drawable.baldhill));
        ImgDrawable.add(getResources().getDrawable(R.drawable.android));
        ImgDrawable.add(getResources().getDrawable(R.drawable.ios));
        ImgDrawable.add(getResources().getDrawable(R.drawable.androids));

        gm = new GameModel(numOfImg, ImgDrawable);


        resetImg();

        Log.e("MainActivity", "Testing");
        Log.e("MainActivity", gm.description());
        Log.e("MainActivity", Integer.toString(tileView.size()));



    }

    public void resetImg()
    {
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
    }

    @Override
    public void didSelectTile(TileView tileView) {
            int lastTap = tileView.getTileIndex();

            gm.setGameInterface(this);
            gm.pushTileIndex(lastTap);
            Log.e("MainActivity", Integer.toString(lastTap));

            //didMatchTile(gm, gm.LastTapped, gm.SecondTapped);
            //didFailToMatchTile(gm,gm.LastTapped,gm.SecondTapped);
            //scoreDidUpdate(gm);
            //gameDidComplete(gm);

    }

    @Override
    public void gameDidComplete(final GameModel gameModel) {

        new AlertDialog.Builder(this)
                .setTitle("Game Result")
                .setMessage("Your score "+gameModel.gameScore+" Continue the game?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        gameModel.reset(numOfImg, ImgDrawable);
                        resetImg();
                        scoreDidUpdate(gameModel);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


            Log.e("MainActivity", "End");

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
            }, 1000);
            gameModel.MatchCounter++;
            gameModel.gameScore+=200;
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
            }, 1000);
            gameModel.gameScore-=100;
        }
    }

    @Override
    public void scoreDidUpdate(GameModel gameModel) {

        TextView scoreView = (TextView)findViewById(R.id.score);

        scoreView.setText(Integer.toString(gameModel.gameScore));


    }
}
