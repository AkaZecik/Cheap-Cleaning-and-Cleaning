package com.CheapCleaningAndCleaning.GameStates;

import java.util.ArrayList;

public interface GameState {
    void update(ArrayList<Integer> pressedKeys);
    void renderImage();
    void init();
}
