package com.sample.demo.csci342a1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by ChenYiTai on 4/4/16.
 */

public class TileView extends LinearLayout {

    private ImageView imgView;

    public int getTileIndex() {
        return tileIndex;
    }

    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    private int tileIndex;

    private TileViewInterface tileViewInterface;

    public interface TileViewInterface
    {
        void didSelectTile(TileView tileView);
    }

    public TileView(Context context,AttributeSet attrs)
    {
        super(context, attrs);


        imgView = new ImageView(context);
        imgView.setImageResource(R.drawable.question);

        this.addView(imgView);

/*
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setImageResource(R.drawable.lake);
                coverImage();
            }
        });*/

    }

    public void revailImage(final Drawable usedImg)
    {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                imgView.setImageDrawable(usedImg);
                //imgView.setImageResource(R.drawable.lake);

            }
        });

    }
    public void coverImage()
    {
                imgView.setImageResource(R.drawable.question);
    }

    public void hideTile()
    {

            this.setBackgroundColor(Color.WHITE);
            imgView.setImageDrawable(null);
    }

    public void nextViewToLand()
    {
        if(tileViewInterface == null)
        {
            Log.e("Error", "No listener set");
            return;
        }
        tileViewInterface.didSelectTile(this);
    }


    public void setInterface(TileViewInterface tileViewInterface)
    {
        this.tileViewInterface = tileViewInterface;
    }


}
