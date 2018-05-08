package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationState;
import com.badlogic.gdx.Game;

public class PlayingState implements ApplicationState {
    private PlayingState() {
    }

    public static PlayingState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {

    }

    @Override
    public void update(Game entity) {

    }

    @Override
    public void exit(Game entity) {

    }

    @Override
    public void render() {

    }

    private static class InstanceHolder {
        static PlayingState instance = new PlayingState();
    }
}
