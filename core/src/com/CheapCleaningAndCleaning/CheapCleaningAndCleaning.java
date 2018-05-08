package com.CheapCleaningAndCleaning;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.PlayingState;
import com.badlogic.gdx.Game;

public class CheapCleaningAndCleaning extends Game {
    private ApplicationStackStateMachine stateStack;
    private long time = 0;
    private long currentSystemTime;
    private long accumulator = 0;

    @Override
    public void create() {
        stateStack = new ApplicationStackStateMachine(this, PlayingState.getInstance());
        currentSystemTime = System.nanoTime();
    }

    @Override
    public void render() {
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
            time += deltaTime;
        }
        //render
        stateStack.render();
    }
}
