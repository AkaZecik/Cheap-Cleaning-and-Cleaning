package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.Player;

import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.GameObject;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Player extends GameObject {
    private long positionX = 0;
    private long positionY = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        BitmapFont font = new BitmapFont();
        font.draw(batch, "x: " + positionX + ", y: " + positionY, 200, 200);
    }

    public void moveRight() {
        positionX += 10;
    }

    public void moveLeft() {
        positionX -= 10;
    }

    public void moveUp() {
        positionY += 10;
    }

    public void moveDown() {
        positionY -= 10;
    }
}
