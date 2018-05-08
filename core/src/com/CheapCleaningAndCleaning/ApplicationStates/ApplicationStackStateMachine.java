package com.CheapCleaningAndCleaning.ApplicationStates;

import com.CheapCleaningAndCleaning.State.StackStateMachine;
import com.badlogic.gdx.Game;

public class ApplicationStackStateMachine extends StackStateMachine<Game, ApplicationState> {
    public ApplicationStackStateMachine() {
        super();
    }

    public ApplicationStackStateMachine(Game owner) {
        super(owner);
    }

    public ApplicationStackStateMachine(Game owner, ApplicationState initialState) {
        super(owner, initialState);
    }

    public ApplicationStackStateMachine(Game owner, ApplicationState initialState, ApplicationState globalState) {
        super(owner, initialState, globalState);
    }

    public void render() {
        if (getGlobalState() != null) {
            getGlobalState().render();
        }

        if (getCurrentState() != null) {
            getCurrentState().render();
        }
    }
}
