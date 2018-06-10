package com.CheapCleaningAndCleaning.ApplicationStates;

import com.CheapCleaningAndCleaning.ApplicationStates.ChoosingDifficultyState.ChoosingDifficultyState;
import com.CheapCleaningAndCleaning.ApplicationStates.ChoosingSaveState.ChoosingSaveState;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.PlayingState;
import com.CheapCleaningAndCleaning.ApplicationStates.PreviousState.PreviousState;
import com.CheapCleaningAndCleaning.State.StackStateMachine;
import com.badlogic.gdx.Game;

public class ApplicationStackStateMachine extends StackStateMachine<Game, AbstractApplicationState> {
    private AbstractApplicationState nextState;

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

    public void transitionToState(AbstractApplicationState nextState) {
        if (nextState == null) {
            throw new NullPointerException("nextState is null");
        }

        this.nextState = nextState;
    }

    @Override
    public boolean revertToPreviousState() {
        nextState = PreviousState.getInstance();
        return true;
    }

    public void processTransitions() {
        if (nextState == null) {
            return;
        }

        AbstractApplicationState theNextState = nextState;
        nextState = null;

        if (theNextState == PreviousState.getInstance()) {
            super.revertToPreviousState();
            getCurrentState().setInputProcessor();
            return;
        }

        if (theNextState == PlayingState.getInstance()) {
            clearStack();
            putState(theNextState);
            return;
        }

        if (theNextState == ChoosingSaveState.getInstance()) {
            putState(theNextState);
            return;
        }

        if (theNextState == ChoosingDifficultyState.getInstance()) {
            putState(theNextState);
            return;
        }

        changeState(theNextState);
    }
}
