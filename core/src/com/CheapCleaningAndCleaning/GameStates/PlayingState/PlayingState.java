package com.CheapCleaningAndCleaning.GameStates.PlayingState;

import com.CheapCleaningAndCleaning.GameStates.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class PlayingState implements GameState {
    private int playerPositionX = 0;
    private int playerPositionY = 0;

    @Override
    public void update(ArrayList<Integer> pressedKeys) {
        for (Integer i : pressedKeys) {
            if (i == Input.Keys.A) {
                playerPositionX--;
            }
            if (i == Input.Keys.D) {
                playerPositionX++;
            }
            if (i == Input.Keys.W) {
                playerPositionY++;
            }
            if (i == Input.Keys.S) {
                playerPositionY--;
            }
        }
    }

    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void init() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void renderImage() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, "x: " + playerPositionX + " y: " + playerPositionY, 200, 200);
        batch.end();
    }
}
