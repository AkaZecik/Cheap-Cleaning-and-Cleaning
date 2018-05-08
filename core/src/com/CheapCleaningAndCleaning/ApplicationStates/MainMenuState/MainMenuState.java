package com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.badlogic.gdx.Game;

public class MainMenuState extends AbstractApplicationState {
    private MainMenuState() {

    }

    public static MainMenuState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);
    }

    @Override
    public void update(Game entity) {
        super.update(entity);
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
        static MainMenuState instance = new MainMenuState();
    }
}
