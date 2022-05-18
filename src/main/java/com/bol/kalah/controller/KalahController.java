package com.bol.kalah.controller;

import com.bol.kalah.model.Game;
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
        return ResponseEntity.ok().body(service.move(gameId, pitId));
    }
}
