package com.bol.kalah.rule.runner;

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
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
public class GameRunnerTest {

    @Autowired
    private GameRunner gameRunner;

    @Test
    public void testPlayGame() {
        // Given
        var game = new Game();
        var player2PitId = 3;

        // When
        gameRunner.run(game, player2PitId);

        // Then
        var pits = game.getBoard().getPits();
        assertEquals(14, pits.size());
        assertEquals(Player.PLAYER_1, game.getTurn());
        assertNull(game.getWinner());
    }
}
