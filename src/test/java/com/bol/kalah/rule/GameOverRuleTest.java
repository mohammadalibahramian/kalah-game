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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
public class GameOverRuleTest {

    @Autowired
    private GameRunner gameRunner;

    @Test
    public void testGameOver() {
        // Given
        var game = new Game();
        game.getBoard().getPits()
                .stream()
                .filter(pit -> !pit.isHouse())
                .forEach(pit -> pit.setStoneCount(0));
        game.getBoard().getPits().get(5).setStoneCount(1);

        // When
        gameRunner.run(game, 6);

        // Then
        assertEquals(Player.PLAYER_2, game.getWinner());
    }

    @Test
    public void testDetermineWinner() {
        // Given
        var game = new Game();
        var stonesInHouseForPlayer1 = 52;
        var stonesInHouseForPlayer2 = 19;
        var lastStonePitId = 6;

        var pits = game.getBoard().getPits();
        pits.stream()
                .filter(pit -> !pit.isHouse())
                .forEach(pit -> pit.setStoneCount(0));
        pits.get(Player.PLAYER_1.getHouseIndex() - 1).setStoneCount(stonesInHouseForPlayer1);
        pits.get(Player.PLAYER_2.getHouseIndex() - 1).setStoneCount(stonesInHouseForPlayer2);
        pits.get(lastStonePitId - 1).setStoneCount(1);

        // When
        gameRunner.run(game, lastStonePitId);

        // Then
        assertNotNull(game.getWinner());
        assertEquals(Player.PLAYER_1, game.getWinner());
    }
}
