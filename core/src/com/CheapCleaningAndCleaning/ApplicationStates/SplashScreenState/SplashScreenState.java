package com.CheapCleaningAndCleaning.ApplicationStates.SplashScreenState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.badlogic.gdx.Game;

public class SplashScreenState extends AbstractApplicationState {
    private SplashScreenState() {

    }

    public static SplashScreenState getInstance() {
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
        static SplashScreenState instance = new SplashScreenState();
    }
}
