package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.badlogic.gdx.Game;

public class PlayingState extends AbstractApplicationState {
    private PlayingState() {

    }

    public static PlayingState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);
    }

    @Override
    public void update(Game entity) {

    }

    @Override
    public void exit(Game entity) {
        super.exit(entity);
    }

    @Override
    public void render() {
        super.render();
    }

    private static class InstanceHolder {
        static PlayingState instance = new PlayingState();
    }
}
