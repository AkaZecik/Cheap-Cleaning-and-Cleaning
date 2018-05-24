package com.CheapCleaningAndCleaning.ApplicationStates;

import com.CheapCleaningAndCleaning.State.StackStateMachine;
import com.badlogic.gdx.Game;

public class ApplicationStackStateMachine extends StackStateMachine<Game, AbstractApplicationState> {
    public ApplicationStackStateMachine() {
        super();
    }

    public ApplicationStackStateMachine(Game owner) {
        super(owner);
    }

    public ApplicationStackStateMachine(Game owner, AbstractApplicationState initialState) {
        super(owner, initialState);
    }

    public ApplicationStackStateMachine(Game owner, AbstractApplicationState initialState, AbstractApplicationState globalState) {
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

    @Override
    public void changeState(AbstractApplicationState newState, boolean replaceCurrentState) {
        System.out.println("State change [" + System.currentTimeMillis() + ", " + newState + "]");
        super.changeState(newState, replaceCurrentState);
    }

    public void handleInput() {
        if (getCurrentState() != null && getCurrentState().nextState != null) {
            changeState(getCurrentState().nextState, true);
        }
    }
}
