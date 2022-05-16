package com.bol.kalah.controller;

import com.bol.kalah.exception.ApplicationException;
import com.bol.kalah.model.Board;
import com.bol.kalah.model.Game;
import com.bol.kalah.model.Player;
import com.bol.kalah.service.KalahService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller provides two apis for creating and playing game
 * <ul>
 *     <li> /api/games for creating a new game </li>
 *     <li> /api/games/{gameId}/{pitId} for starting game by selecting a pit id </li>
 * </ul>
 */
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class KalahController {

    private final KalahService service;

    @PostMapping("/games")
    public ResponseEntity<Game> createGame() {
        log.debug("hit /api/games");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createGame());
    }

    @PutMapping("/games/{gameId}/pits/{pitId}")
    public ResponseEntity<Game> move(@PathVariable final String gameId, @PathVariable final Integer pitId) {
        log.debug("hit /api/games/{}/pits/{}", gameId, pitId);
        if (pitId < 1 || pitId > Board.PIT_END_INDEX || pitId == Player.PLAYER_1.getHouseIndex() || pitId == Player.PLAYER_2.getHouseIndex()) {
            throw new ApplicationException("The pit id is invalid, it should be between 1...6 or 7...13");
        }
        return ResponseEntity.ok().body(service.move(gameId, pitId));
    }
}
