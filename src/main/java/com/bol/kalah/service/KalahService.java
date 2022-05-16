package com.bol.kalah.service;

import com.bol.kalah.model.Game;

public interface KalahService {

    /**
     * Creates new game
     * @return new game
     */
    Game createGame();

    /**
     * Moves stones from selected pitId and update game,board and pits status according to the selected pitId
     * @param gameId
     * @param pitId
     * @return Game which is updated according to this move
     */
    Game move(String gameId, Integer pitId);
}
