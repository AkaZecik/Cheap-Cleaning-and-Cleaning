package com.CheapCleaningAndCleaning.ApplicationStates;

import com.CheapCleaningAndCleaning.ApplicationStates.ChoosingDifficultyState.ChoosingDifficultyState;
import com.CheapCleaningAndCleaning.ApplicationStates.ChoosingSaveState.ChoosingSaveState;
import com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState.MainMenuState;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.PlayingState;
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
            AbstractApplicationState current = getCurrentState();
            AbstractApplicationState next = current.nextState;

            if (next == PlayingState.getInstance()) {
                dropState();
                dropState();
                dropState();
                changeState(next, false);
                return;
            }

            if (next == ChoosingSaveState.getInstance() && current == ChoosingDifficultyState.getInstance()) {
                changeState(next, false);
                return;
            }

            if (next == ChoosingDifficultyState.getInstance()) {
                if (current == MainMenuState.getInstance()) {
                    changeState(next, false);
                    return;
                }

                if (current == ChoosingSaveState.getInstance()) {
                    dropState();
                }
            }

            changeState(next, true);
        }
    }

    public void dropState() {
        if (stateStack.size > 0) {
            stateStack.pop().exit(owner);
        }
    }
}
