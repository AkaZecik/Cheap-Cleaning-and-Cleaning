package com.CheapCleaningAndCleaning.ApplicationStates.SplashScreenState;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationState;

public class SplashScreenState implements ApplicationState {
    private SplashScreenState() {

    }

    public static SplashScreenState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter() {

    }

    @Override
    public void update() {

    }

    @Override
    public void renderImage() {

    }

    private static class InstanceHolder {
        static SplashScreenState instance = new SplashScreenState();
    }
}
