package com.bol.kalah.repository.impl;

import com.bol.kalah.exception.GameNotFoundException;
import com.bol.kalah.model.Game;
import com.bol.kalah.repository.KalahRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryRepository implements KalahRepository {

    private final Map<String, Game> gameMap = new ConcurrentHashMap<>();

    @Override
    public Game save(final Game game) {
        gameMap.put(game.getId(), game);
        return find(game.getId());
    }

    @Override
    public Game find(final String id) {
        Game game = gameMap.get(id);
        if (game == null) {
            throw new GameNotFoundException("Could not find game " + id);
        }
        return game;
    }
}
