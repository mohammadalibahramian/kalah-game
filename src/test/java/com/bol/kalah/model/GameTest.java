package com.bol.kalah.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void createNewGameTest() {
        var game = new Game();
        // Then
        assertNotNull(game.getId());
        assertNotNull(game.getBoard());
        assertNotNull(game.getBoard().getPits());
        assertNull(game.getTurn());
    }

    @Test
    public void playerTurnTest() {
        // Given / then
        var player = Player.PLAYER_2;
        var game = new Game();

        // Then
        assertNull(game.getTurn());

        game.setTurn(player);

        assertEquals(player, game.getTurn());
    }
}
