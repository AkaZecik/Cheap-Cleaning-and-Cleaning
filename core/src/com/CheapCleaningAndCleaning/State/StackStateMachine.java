package com.CheapCleaningAndCleaning.State;

import com.badlogic.gdx.utils.Array;

import java.util.Objects;

public class StackStateMachine<E, S extends State<E>> implements StateMachine<E, S> {
    protected E owner;
    protected Array<S> stateStack;
    protected S globalState;

    public StackStateMachine() {
        this(null, null, null);
    }

    public StackStateMachine(E owner) {
        this(owner, null, null);
    }

    public StackStateMachine(E owner, S initialState) {
        this(owner, initialState, null);
    }

    public StackStateMachine(E owner, S initialState, S globalState) {
        this.owner = owner;
        this.globalState = globalState;
        this.setInitialState(initialState);
    }

    @Override
    public void update() {
        if (getGlobalState() != null) {
            getGlobalState().update(owner);
        }

        if (getCurrentState() != null) {
            getCurrentState().update(owner);
        }
    }

    @Override
    public void changeState(S newState) {
        changeState(newState, false);
    }

    public void changeState(S newState, boolean replaceCurrentState) {
        if (replaceCurrentState) {
            if (stateStack.size > 0) {
                stateStack.pop();
            }
        }

        stateStack.add(newState);
    }

    @Override
    public boolean revertToPreviousState() {
        if (stateStack.size == 0) {
            return false;
        }

        stateStack.pop();
        return true;
    }

    @Override
    public void setInitialState(S state) {
        if (stateStack == null) {
            stateStack = new Array<>();
        }

        stateStack.clear();
        stateStack.add(state);
    }

    @Override
    public S getCurrentState() {
        return stateStack == null ? null : stateStack.peek();
    }

    @Override
    public S getGlobalState() {
        return globalState;
    }

    @Override
    public boolean isInState(S state) {
        return stateStack != null && Objects.equals(state, getCurrentState());
    }
}
