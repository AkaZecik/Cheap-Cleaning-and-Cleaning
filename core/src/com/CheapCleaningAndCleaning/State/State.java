package com.CheapCleaningAndCleaning.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface State<E> {
    void enter(E entity);
    void update(E entity);
    void exit(E entity);
}
