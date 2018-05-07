package com.CheapCleaningAndCleaning;

import com.CheapCleaningAndCleaning.GameStates.GameState;
import com.CheapCleaningAndCleaning.GameStates.PlayingState.PlayingState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

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
