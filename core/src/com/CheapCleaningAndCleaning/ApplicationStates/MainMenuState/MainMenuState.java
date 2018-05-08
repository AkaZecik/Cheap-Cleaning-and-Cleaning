package com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationState;
import com.badlogic.gdx.Game;

public class MainMenuState implements ApplicationState {
    private MainMenuState() {

    }

    public static MainMenuState getInstance() {
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
        static MainMenuState instance = new MainMenuState();
    }
}
