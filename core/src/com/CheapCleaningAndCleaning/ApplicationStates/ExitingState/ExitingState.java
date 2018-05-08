package com.CheapCleaningAndCleaning.ApplicationStates.ExitingState;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationState;

public class ExitingState implements ApplicationState {
    private ExitingState() {

    }

    public static ExitingState getInstance() {
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
        static ExitingState instance = new ExitingState();
    }
}
