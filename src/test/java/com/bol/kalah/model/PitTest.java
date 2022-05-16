package com.bol.kalah.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PitTest {

    @Test
    public void initPitTest() {
        // Given / when
        var pit1 = new Pit(1);
        var pit2 = new Pit(2);
        var pit3 = new Pit(3);
        var pit4 = new Pit(4);
        var pit9 = new Pit(9);
        var pit10 = new Pit(10);
        // Then
        assertEquals(1, pit1.getId());
        assertEquals(2, pit2.getId());
        assertEquals(3, pit3.getId());
        assertEquals(4, pit4.getId());
        assertEquals(9, pit9.getId());
        assertEquals(10, pit10.getId());
    }

    @Test
    public void stoneCountTest() {
        // Given / when
        var pit3 = new Pit(3);
        var pit7 = new Pit(7);
        // Then
        assertEquals(6, pit3.getStoneCount());
        assertEquals(0, pit7.getStoneCount());
    }

    @Test
    public void housePitTest() {
        // Given / when
        var pit7 = new Pit(7);
        var pit2 = new Pit(2);
        var pit14 = new Pit(14);
        var pit9 = new Pit(9);
        // Then
        assertTrue(pit7.isHouse());
        assertFalse(pit2.isHouse());
        assertTrue(pit14.isHouse());
        assertFalse(pit9.isHouse());
    }

    @Test
    public void pitOwnerTest() {
        // Given / when
        var pit1 = new Pit(1);
        var pit11 = new Pit(11);
        // Then
        assertEquals(Player.PLAYER_2, pit1.getOwner());
        assertEquals(Player.PLAYER_1, pit11.getOwner());
    }
}
