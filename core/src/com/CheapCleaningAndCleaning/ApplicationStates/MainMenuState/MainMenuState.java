package com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationState;

public class MainMenuState implements ApplicationState {
    private MainMenuState() {

    }

    public static MainMenuState getInstance() {
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
        static MainMenuState instance = new MainMenuState();
    }
}
