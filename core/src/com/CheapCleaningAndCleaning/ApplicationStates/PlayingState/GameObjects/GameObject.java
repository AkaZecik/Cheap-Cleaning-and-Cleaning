package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {
    public float x;
    public float y;

    public abstract void update();
    public abstract void draw(SpriteBatch batch);
}
