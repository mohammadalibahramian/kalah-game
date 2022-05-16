package com.bol.kalah.repository;

import com.bol.kalah.exception.GameNotFoundException;
import com.bol.kalah.model.Game;

public interface KalahRepository {

    /**
     * Saves the game
     * @param game to be saved
     * @return saved game
     */
    Game save(final Game game);

    /**
     * Finds game by game id
     * @param id
     * @return game if exists, otherwise throws an exception
     */
    Game find(final String id) throws GameNotFoundException;
}
