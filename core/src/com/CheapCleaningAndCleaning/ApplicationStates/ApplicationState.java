package com.CheapCleaningAndCleaning.ApplicationStates;

import com.CheapCleaningAndCleaning.State.State;
import com.badlogic.gdx.Game;

public interface ApplicationState extends State<Game> {
    void render();
}
