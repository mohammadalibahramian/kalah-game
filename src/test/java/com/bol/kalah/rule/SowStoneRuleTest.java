package com.bol.kalah.rule;

import com.bol.kalah.model.Game;
import com.bol.kalah.model.Player;
import com.bol.kalah.rules.runner.GameRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
public class SowStoneRuleTest {

    @Autowired
    private GameRunner gameRunner;

    @Test
    public void testSowStones() {
        // Given
        var game = new Game();
        var startPitId = 1;

        // When
        gameRunner.run(game, startPitId);

        // Then
        var pits = game.getBoard().getPits();
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
    }

    @Test
    public void testCaptureStonesForLastEmptyPit() {
        // Given
        var game = new Game();
        var pits = game.getBoard().getPits();
        pits.get(5).setStoneCount(0);
        pits.get(4).setStoneCount(1);

        var playerOneHousePitId = 7;
        var opponentPitId = 8;

        // When
        gameRunner.run(game, 5);

        // Then
        assertEquals(7, game.getBoard().getPits().get(playerOneHousePitId - 1).getStoneCount());
        assertEquals(0, game.getBoard().getPits().get(opponentPitId - 1).getStoneCount());

    }

    @Test
    public void testSetPlayerTurn() {
        // Given
        var game = new Game();
        int playerNorthStartPidId = 9;

        // When
        gameRunner.run(game, playerNorthStartPidId);

        // Then
        assertEquals(Player.PLAYER_2, game.getTurn());
    }
}
