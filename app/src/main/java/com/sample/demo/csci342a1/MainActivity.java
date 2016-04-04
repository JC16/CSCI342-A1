package com.sample.demo.csci342a1;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int numOfImg = 6;
        Drawable ImgDrawable[] = {getResources().getDrawable(R.drawable.lake),getResources().getDrawable(R.drawable.cathedral)
                ,getResources().getDrawable(R.drawable.baldhill)};

        GameModel gm = new GameModel(numOfImg, ImgDrawable);

        Log.e("MainActivity","Testing");
        Log.e("MainActivity", gm.description());
    }
}
