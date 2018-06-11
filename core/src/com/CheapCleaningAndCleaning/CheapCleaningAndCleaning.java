package com.CheapCleaningAndCleaning;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.ApplicationStates.SplashScreenState.SplashScreenState;
import com.badlogic.gdx.Game;

public class CheapCleaningAndCleaning extends Game {
    private static ApplicationStackStateMachine stateStack;
    private long currentSystemTime;
    private long accumulator = 0;

    public static ApplicationStackStateMachine getStateMachine() {
        return stateStack;
    }

    @Override
    public void create() {
        stateStack = new ApplicationStackStateMachine(this);
        stateStack.setInitialState(SplashScreenState.getInstance());
        currentSystemTime = System.nanoTime();
    }

    @Override
    public void render() {
        stateStack.processTransitions();
        long newTime = System.nanoTime();
        long frameTime = newTime - currentSystemTime;
        currentSystemTime = newTime;

        if (frameTime > 250_000_000) {
            frameTime = 250_000_000;
        }

        accumulator += frameTime;
        long deltaTime = 10_000_000;

        while (accumulator >= deltaTime) {
            //physics
            stateStack.update();
            accumulator -= deltaTime;
        }

        //render
        stateStack.render();
    }
}
