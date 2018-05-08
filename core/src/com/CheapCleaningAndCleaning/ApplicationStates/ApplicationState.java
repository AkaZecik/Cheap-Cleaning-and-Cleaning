package com.CheapCleaningAndCleaning.ApplicationStates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ApplicationState {
    void enter();
    void update();
    void render(SpriteBatch batch);
}
