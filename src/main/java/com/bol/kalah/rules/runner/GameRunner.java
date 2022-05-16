package com.bol.kalah.rules.runner;

import com.bol.kalah.model.Game;
import com.bol.kalah.rules.GameOverRule;
import com.bol.kalah.rules.GameRule;
import com.bol.kalah.rules.SowStonesRule;
import com.bol.kalah.rules.ValidateMoveRule;
import org.springframework.stereotype.Component;

/**
 * Runs the game by applying game rules
 */
@Component
public class GameRunner {

    private final GameRule gameRule;

    /**
     * Each move should be validated first, so {@link ValidateMoveRule} is set as the first rule.
     * If validation is successful, the move can be executed, so {@link SowStonesRule} is set as the second rule.
     * When a move is executed, the game status should be determined, so {@link GameOverRule} is set as the third rule
     */
    public GameRunner() {
        gameRule = new ValidateMoveRule();
        gameRule
                .setNextRule(new SowStonesRule())
                .setNextRule(new GameOverRule());

    }

    public void run(final Game game, final Integer pitId) {
        this.gameRule.execute(game, pitId);
    }
}
