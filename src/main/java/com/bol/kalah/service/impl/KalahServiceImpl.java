package com.bol.kalah.service.impl;

import com.bol.kalah.model.Game;
import com.bol.kalah.repository.KalahRepository;
import com.bol.kalah.rules.runner.GameRunner;
import com.bol.kalah.service.KalahService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KalahServiceImpl implements KalahService {

    private final KalahRepository repository;
    private final GameRunner gameRunner;

    @Override
    public Game createGame() {
        var newGame = new Game();
        log.debug("new game by game id {} is created", newGame.getId());
        return repository.save(newGame);
    }

    @Override
    public Game move(String gameId, Integer pitId) {
        var game = repository.find(gameId);
        gameRunner.run(game, pitId);
        repository.save(game);
        return game;
    }
}
