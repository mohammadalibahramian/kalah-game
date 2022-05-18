package com.bol.kalah.rules;

import com.bol.kalah.exception.*;
import com.bol.kalah.model.Board;
import com.bol.kalah.model.Game;
import com.bol.kalah.model.Player;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is used to validate each movement
 */
@Slf4j
public class ValidateMoveRule extends GameRule {
    @Override
    public void execute(Game game, Integer pitId) {
        log.debug("start validating move from pit {}", pitId);
        if (pitId < 1 || pitId > Board.PIT_END_INDEX) {
            throw new InvalidPitIdException("The pit id is invalid, it should be between 1...6 or 7...13");
        }
        if (game.getWinner() != null) {
            throw new FinishedGameSelectedException("this game is finished!");
        }
        var startPit = game.getBoard().getPit(pitId);
        if (startPit.isHouse()) {
            throw new HouseMoveException("No movement on house pits");
        }
        if (Player.PLAYER_2.equals(game.getTurn())
                && !Player.PLAYER_2.equals(startPit.getOwner())) {
            throw new WrongTurnException("It is Player 2's turn");
        }
        if (Player.PLAYER_1.equals(game.getTurn())
                && !Player.PLAYER_1.equals(startPit.getOwner())) {
            throw new WrongTurnException("It is Player 1's turn");
        }
        if (startPit.getStoneCount() == 0) {
            throw new EmptyPitMoveException("Can not move from empty pit");
        }
        if (game.getTurn() == null) {
            if (Player.PLAYER_2.equals(startPit.getOwner())) {
                game.setTurn(Player.PLAYER_2);
            } else {
                game.setTurn(Player.PLAYER_1);
            }
        }
        log.debug("validation is successful, can move from pit {}", pitId);
        nextRule.execute(game, pitId);
    }
}
