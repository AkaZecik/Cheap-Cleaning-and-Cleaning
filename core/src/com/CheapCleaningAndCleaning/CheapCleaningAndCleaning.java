package com.CheapCleaningAndCleaning;

import com.CheapCleaningAndCleaning.GameStates.GameState;
import com.CheapCleaningAndCleaning.GameStates.PlayingState.PlayingState;
import com.badlogic.gdx.ApplicationAdapter;

public class CheapCleaningAndCleaning extends ApplicationAdapter {
    GameState currentGameState;

    @Override
    public void create() {
        currentGameState = new PlayingState();
        currentGameState.init();
    }

    private long time = 0;
    private long currentSystemTime = System.nanoTime();
    private long accumulator = 0;

    @Override
    public void render() {
        long newTime = System.nanoTime();
        long frameTime = newTime - currentSystemTime;
        currentSystemTime = newTime;
        if (frameTime > 250000000) {
            frameTime = 250000000;
        }
        accumulator += frameTime;
        long deltaTime = 10000000;
        while (accumulator >= deltaTime) {
            //physics
            currentGameState.update();

            accumulator -= deltaTime;
            time += deltaTime;
        }
        //render
        currentGameState.renderImage();
    }
}
