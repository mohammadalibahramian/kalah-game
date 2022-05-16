package com.bol.kalah.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pit {

    private final int id;
    private int stoneCount;

    @JsonCreator
    public Pit(@JsonProperty("id") final int id) {
        this.id = id;
        if (!this.isHouse()) {
            this.setStoneCount(6);
        }
    }

    public int getId() {
        return this.id;
    }

    public Player getOwner() {
        if (this.getId() <= Player.PLAYER_2.getHouseIndex()) {
            return Player.PLAYER_2;
        } else {
            return Player.PLAYER_1;
        }
    }

    public int getStoneCount() {
        return this.stoneCount;
    }

    public void setStoneCount(final int stoneCount) {
        this.stoneCount = stoneCount;
    }

    public boolean checkIfThePitIsNotOpponentHouse(final Player turn) {
        return (!turn.equals(Player.PLAYER_2)
                || (this.getId() != Player.PLAYER_1.getHouseIndex()))
                && (!turn.equals(Player.PLAYER_1)
                || (this.getId() != Player.PLAYER_2.getHouseIndex()));
    }

    public boolean isHouse() {
        return (this.getId() == Player.PLAYER_2.getHouseIndex())
                || (this.getId() == Player.PLAYER_1.getHouseIndex());
    }
}
