package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player;

import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends GameObject {
    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y--;
        }
    }

    @Override
    public void draw() {
        SpriteBatch batch = new SpriteBatch();
        BitmapFont font = new BitmapFont();
        batch.begin();
        font.draw(batch, "x: " + x + " y: " + y, 200, 200);
        batch.end();
    }
}
