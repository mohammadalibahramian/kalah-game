package com.bol.kalah.rule;

import com.bol.kalah.exception.*;
import com.bol.kalah.model.Game;
import com.bol.kalah.model.Player;
import com.bol.kalah.rules.runner.GameRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
public class ValidateMoveRuleTest {

    @Autowired
    private GameRunner gameRunner;

    @Test
    public void testStartMoveFromEmptyPit() {
        // Given
        var game = new Game();
        var pit = game.getBoard().getPits().get(2);
        pit.setStoneCount(0);

        // When / Then
        var emptyPitMoveException = assertThrows(
                EmptyPitMoveException.class,
                () -> gameRunner.run(game, 3),
                "Expected play game throws an emptyPitMoveException"
        );
        assertTrue(emptyPitMoveException.getMessage().contains("Can not move from empty pit"));
    }

    @Test
    public void testStartMoveFromHouse() {
        // Given
        var game = new Game();
        var startMove = 14;

        // When / Then
        var houseMoveException = assertThrows(
                HouseMoveException.class,
                () -> gameRunner.run(game, startMove),
                "Expected play game throws an houseMoveException"
        );
        assertTrue(houseMoveException.getMessage().contains("No movement on house pits"));
    }

    @Test
    public void testWrongTurn() {
        // Given
        var game = new Game();
        game.setTurn(Player.PLAYER_1);
        var startMove = 4;

        // When / Then
        var wrongTurnException = assertThrows(
                WrongTurnException.class,
                () -> gameRunner.run(game, startMove),
                "Expected play game throws an wrongTurnException"
        );
        assertTrue(wrongTurnException.getMessage().contains("It is Player 1's turn"));
    }

    @Test
    public void testMoveWithInvalidPitId() {
        // Given
        var game = new Game();
        game.setTurn(Player.PLAYER_1);
        var invalidPitId = 19;

        // When / Then
        var invalidPitIdException = assertThrows(
                InvalidPitIdException.class,
                () -> gameRunner.run(game, invalidPitId),
                "Expected play game throws an invalidPitIdException"
        );
        assertTrue(invalidPitIdException.getMessage().contains("The pit id is invalid, it should be between 1...6 or 7...13"));
    }

    @Test
    public void testMoveWhenGameIsFinished() {
        // Given
        var game = new Game();
        game.setWinner(Player.PLAYER_1);
        var startMove = 3;

        // When / Then
        var finishedGameSelectedException = assertThrows(
                FinishedGameSelectedException.class,
                () -> gameRunner.run(game, startMove),
                "Expected play game throws an finishedGameSelectedException"
        );
        assertTrue(finishedGameSelectedException.getMessage().contains("this game is finished!"));
    }
}
