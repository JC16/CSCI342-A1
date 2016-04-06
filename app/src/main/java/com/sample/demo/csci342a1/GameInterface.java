package com.sample.demo.csci342a1;

/**
 * Created by ChenYiTai on 4/4/16.
 */
public interface GameInterface {

    void gameDidComplete(GameModel gameModel);
    void didMatchTile(GameModel gameModel, int tileIndex, int previousTileIndex);
    void didFailToMatchTile(GameModel gameModel,int tileIndex,int previousTileIndex);
    void scoreDidUpdate(GameModel gameModel);

}
