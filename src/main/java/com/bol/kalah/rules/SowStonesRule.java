package com.bol.kalah.rules;

import com.bol.kalah.model.Board;
import com.bol.kalah.model.Game;
import com.bol.kalah.model.Pit;
import com.bol.kalah.model.Player;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is used for sowing stones on to the right
 */
@Slf4j
public class SowStonesRule extends GameRule {
    @Override
    public void execute(Game game, Integer pitId) {
        log.debug("executing move from pit {}", pitId);
        var currentPit = game.getBoard().getPit(pitId);
        var stones = currentPit.getStoneCount();
        currentPit.setStoneCount(0);
        while (stones > 0) {
            var nextPit = game.getBoard().getPit(++pitId);
            if (nextPit.checkIfThePitIsNotOpponentHouse(game.getTurn())) {
                nextPit.setStoneCount(nextPit.getStoneCount() + 1);
                stones--;
            }
        }
        captureStonesForLastEmptyPit(game, pitId);
        setPlayerTurn(game, pitId);
        nextRule.execute(game, pitId);
    }

    /**
     * when the last stone lands in an own empty pit,
     * the player captures his own stone and all stones in the opponent pit
     * and puts them in his own house
     */
    private void captureStonesForLastEmptyPit(final Game game, final int endPitId) {
        var endPit = game.getBoard().getPit(endPitId);
        if (!endPit.isHouse() && endPit.getOwner().equals(game.getTurn()) && (endPit.getStoneCount() == 1)) {
            var opponentPit = game.getBoard().getPit(Board.PIT_END_INDEX - endPit.getId());
            if (opponentPit.getStoneCount() > 0) {
                log.debug("capture stones for last empty pit for player {}", endPit.getOwner());
                Pit house = game.getBoard().getPit(endPit.getOwner().getHouseIndex());
                house.setStoneCount(
                        (house.getStoneCount() + opponentPit.getStoneCount()) + endPit.getStoneCount());
                opponentPit.setStoneCount(0);
                endPit.setStoneCount(0);
            }
        }
    }

    private void setPlayerTurn(final Game game, final int pitId) {
        var pit = game.getBoard().getPit(pitId);
        // if the current pit is house, current player should continue
        if (pit.isHouse()) {
            if (Player.PLAYER_2.equals(pit.getOwner()) && Player.PLAYER_2.equals(game.getTurn())) {
                game.setTurn(Player.PLAYER_2);
            } else if (Player.PLAYER_1.equals(pit.getOwner()) && Player.PLAYER_1.equals(game.getTurn())) {
                game.setTurn(Player.PLAYER_1);
            }
        }
        // otherwise, just switch the turn
        else {
            if (Player.PLAYER_2.equals(game.getTurn())) {
                game.setTurn(Player.PLAYER_1);
            } else {
                game.setTurn(Player.PLAYER_2);
            }
        }
        log.debug("next turn is: {}", game.getTurn());
    }
}
