package com.bol.kalah.rules;

import com.bol.kalah.model.Game;
import com.bol.kalah.model.Player;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is used to check if game is over and determine the winner.
 * The game is over as soon as one of the sides runs out of stones.
 * The player who still has stones in his pits keeps them and puts them in his house.
 * The winner of the game is the player who has the most stones in his house.
 */
@Slf4j
public class GameOverRule extends GameRule{
    @Override
    public void execute(final Game game, final Integer pitId) {
        var player2Stones = game.getBoard().getStoneCount(Player.PLAYER_2, false);
        var player1Stones = game.getBoard().getStoneCount(Player.PLAYER_1, false);
        if ((player2Stones == 0) || (player1Stones == 0)) {
            log.debug("the game is over, calculating stones...");
            var player2House = game.getBoard().getPit(Player.PLAYER_2.getHouseIndex());
            var player1House = game.getBoard().getPit(Player.PLAYER_1.getHouseIndex());
            player2House.setStoneCount(player2House.getStoneCount() + player2Stones);
            player1House.setStoneCount(player1House.getStoneCount() + player1Stones);
            determineWinner(game);
            resetBoard(game);
        }
    }

    private void determineWinner(final Game game) {
        int player2AllStones = game.getBoard().getStoneCount(Player.PLAYER_2, true);
        int player1AllStones = game.getBoard().getStoneCount(Player.PLAYER_1, true);
        if (player2AllStones > player1AllStones) {
            game.setWinner(Player.PLAYER_2);
        } else if (player2AllStones < player1AllStones) {
            game.setWinner(Player.PLAYER_1);
        }
        log.debug("the winner is: {}", game.getWinner());
    }

    private void resetBoard(final Game game) {
        game.getBoard().getPits().stream()
                .filter(pit -> !pit.isHouse())
                .forEach(pit -> pit.setStoneCount(0));
    }
}
