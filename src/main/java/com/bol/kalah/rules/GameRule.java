package com.bol.kalah.rules;

import com.bol.kalah.model.Game;

/**
 * An abstract class to define game rules,
 * All game rules should extend this class
 */
public abstract class GameRule {

    protected GameRule nextRule;

    public GameRule setNextRule(GameRule nextRule) {
        this.nextRule = nextRule;
        return nextRule;
    }

    public abstract void execute(Game game, Integer pitId);
}
