package com.bol.kalah.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BordTest {

    @Test
    public void createNewBoardTest() {
        // Given / when
        var board = new Board();
        var pits = board.getPits();

        // Then
        assertNotNull(board);

        assertEquals(1, Board.PIT_START_INDEX);
        assertEquals(14, Board.PIT_END_INDEX);

        assertNotNull(pits);
        assertEquals(14, pits.size());
    }

    @Test
    public void initialStoneCountTest() {
        // Given / when
        var board = new Board();
        var pits = board.getPits();

        // Then
        assertNotNull(board);
        assertNotNull(pits);
        assertEquals(14, pits.size());
        // Player one stones
        assertEquals(6, pits.get(0).getStoneCount());
        assertEquals(6, pits.get(1).getStoneCount());
        assertEquals(6, pits.get(2).getStoneCount());
        assertEquals(6, pits.get(3).getStoneCount());
        assertEquals(6, pits.get(4).getStoneCount());
        assertEquals(6, pits.get(5).getStoneCount());
        // Player one house stones
        assertEquals(0, pits.get(6).getStoneCount());

        // Player two stones
        assertEquals(6, pits.get(7).getStoneCount());
        assertEquals(6, pits.get(8).getStoneCount());
        assertEquals(6, pits.get(9).getStoneCount());
        assertEquals(6, pits.get(10).getStoneCount());
        assertEquals(6, pits.get(11).getStoneCount());
        assertEquals(6, pits.get(12).getStoneCount());
        // Player two house stones
        assertEquals(0, pits.get(13).getStoneCount());
    }

    @Test
    public void getPitTest() {
        // Given
        var pitIndex = 4;
        var board = new Board();

        // When
        var pit = board.getPit(pitIndex);

        // Then
        assertEquals(pitIndex, pit.getId());
        assertEquals(Player.PLAYER_2, pit.getOwner());
    }
}
