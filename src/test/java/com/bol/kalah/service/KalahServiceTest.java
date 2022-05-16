package com.bol.kalah.service;

import com.bol.kalah.model.Board;
import com.bol.kalah.model.Game;
import com.bol.kalah.model.Pit;
import com.bol.kalah.model.Player;
import com.bol.kalah.repository.KalahRepository;
import com.bol.kalah.rules.runner.GameRunner;
import com.bol.kalah.service.impl.KalahServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
public class KalahServiceTest {

    @Mock
    private KalahRepository repository;

    @Spy
    private GameRunner gameRunner;

    @InjectMocks
    private KalahServiceImpl kalahService;

    @Test
    public void createKalahGameTest() {
        // Given
        var game = new Game();
        when(repository.save(any())).thenReturn(game);

        // When
        var createdGame = kalahService.createGame();

        // Then
        assertEquals(game, createdGame);
    }

    @Test
    public void playGameTest() {
        // Given
        var newGame = new Game();
        when(repository.find(newGame.getId())).thenReturn(newGame);
        doCallRealMethod().when(gameRunner).run(newGame, 1);

        // When
        var gameAfterOneMoveFromPitNumberOne = kalahService.move(newGame.getId(), 1);

        // Then
        Board board = gameAfterOneMoveFromPitNumberOne.getBoard();
        assertNotNull(board);

        List<Pit> pits = board.getPits();
        assertNotNull(pits);
        assertEquals(14, pits.size());
        assertEquals(0, pits.get(0).getStoneCount());
        assertEquals(7, pits.get(1).getStoneCount());
        assertEquals(7, pits.get(2).getStoneCount());
        assertEquals(7, pits.get(3).getStoneCount());
        assertEquals(7, pits.get(4).getStoneCount());
        assertEquals(7, pits.get(5).getStoneCount());
        assertEquals(1, pits.get(6).getStoneCount());
        assertEquals(6, pits.get(7).getStoneCount());
        assertEquals(6, pits.get(8).getStoneCount());
        assertEquals(6, pits.get(9).getStoneCount());
        assertEquals(6, pits.get(10).getStoneCount());
        assertEquals(6, pits.get(11).getStoneCount());
        assertEquals(6, pits.get(12).getStoneCount());
        assertEquals(0, pits.get(13).getStoneCount());

        Player playerTurn = gameAfterOneMoveFromPitNumberOne.getTurn();
        assertEquals(Player.PLAYER_2, playerTurn);

        Player winner = gameAfterOneMoveFromPitNumberOne.getWinner();
        assertNull(winner);
    }
}
