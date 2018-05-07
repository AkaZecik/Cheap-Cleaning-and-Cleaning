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
            ArrayList<Integer> pressedKeys = handleInput();
            currentGameState.update(pressedKeys);

            accumulator -= deltaTime;
            time += deltaTime;
        }
        //render
        currentGameState.renderImage();
    }

    private ArrayList<Integer> handleInput() {
        ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            pressedKeys.add(Input.Keys.A);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            pressedKeys.add(Input.Keys.D);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            pressedKeys.add(Input.Keys.W);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            pressedKeys.add(Input.Keys.S);
        }
        return pressedKeys;
    }
}
