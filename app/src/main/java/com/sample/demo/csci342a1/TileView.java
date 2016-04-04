package com.sample.demo.csci342a1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
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

    private int tileIndex;

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


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setImageResource(R.drawable.lake);
            }
        });

    }

    public void revailImage(final Drawable usedImg)
    {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //imgView.setImageDrawable(usedImg);
                imgView.setImageResource(R.drawable.lake);

            }
        });

    }
    public void coverImage()
    {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setImageResource(R.drawable.question);
            }
        });
    }

    public void hideTile(boolean match)
    {
        if(match)
        {
            this.setBackgroundColor(Color.WHITE);
        }
    }



}
