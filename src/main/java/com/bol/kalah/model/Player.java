package com.bol.kalah.model;

public enum Player {
    PLAYER_1(Board.PIT_END_INDEX),
    PLAYER_2(Board.PIT_END_INDEX / 2);

    private final int houseIndex;

    Player(final int houseIndex) {
        this.houseIndex = houseIndex;
    }

    public int getHouseIndex() {
        return this.houseIndex;
    }
}
