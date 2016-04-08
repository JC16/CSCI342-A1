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

    private Drawable image;

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

        final TileView view = this;

        imgView = new ImageView(context);

        this.addView(imgView);


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setImageDrawable(image);

                tileViewInterface.didSelectTile(view);

            }
        });

    }

    public void revailImage(final Drawable usedImg)
    {

        image = usedImg;

    }
    public void coverImage()
    {
                imgView.setImageResource(R.drawable.question);
                imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);

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
