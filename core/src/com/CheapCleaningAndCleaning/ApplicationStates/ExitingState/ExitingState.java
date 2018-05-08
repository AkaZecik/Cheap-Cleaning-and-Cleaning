package com.CheapCleaningAndCleaning.ApplicationStates.ExitingState;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationState;
import com.badlogic.gdx.Game;

public class ExitingState implements ApplicationState {
    private ExitingState() {

    }

    public static ExitingState getInstance() {
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
        static ExitingState instance = new ExitingState();
    }
}
