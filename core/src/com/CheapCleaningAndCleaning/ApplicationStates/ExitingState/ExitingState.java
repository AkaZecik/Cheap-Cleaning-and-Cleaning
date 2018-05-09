package com.CheapCleaningAndCleaning.ApplicationStates.ExitingState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ExitingState extends AbstractApplicationState {
    private ExitingState() {

    }

    public static ExitingState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);
        Gdx.app.exit();
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
        static ExitingState instance = new ExitingState();
    }
}
