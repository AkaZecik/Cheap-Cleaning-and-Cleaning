package com.CheapCleaningAndCleaning.ApplicationStates.SplashScreenState;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationState;
import com.badlogic.gdx.Game;

public class SplashScreenState implements ApplicationState {
    private SplashScreenState() {

    }

    public static SplashScreenState getInstance() {
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
        static SplashScreenState instance = new SplashScreenState();
    }
}
