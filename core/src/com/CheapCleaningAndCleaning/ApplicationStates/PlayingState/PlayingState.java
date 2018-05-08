package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.GameObject;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

public class PlayingState implements ApplicationState {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    private PlayingState() {
    }

    public static PlayingState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    @Override
    public void enter() {
        gameObjects.add(new Player());
    }

    @Override
    public void renderImage() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (GameObject gameObject : gameObjects) {
            gameObject.draw();
        }
    }

    private static class InstanceHolder {
        static PlayingState instance = new PlayingState();
    }
}
