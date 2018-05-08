package com.CheapCleaningAndCleaning.ApplicationStates;

import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.PlayingState;

import java.util.ArrayList;

public class ApplicationStatesManager {
    private ArrayList<ApplicationState> statesArray = new ArrayList<>();

    public ApplicationStatesManager() {
        addState(PlayingState.getInstance());
    }

    private ApplicationState getLast() {
        return statesArray.get(statesArray.size() - 1);
    }

    private void addState(ApplicationState state) {
        statesArray.add(state);
        getLast().enter();
    }

    public void update() {
        getLast().update();
    }

    public void renderImage() {
        getLast().renderImage();
    }
}
