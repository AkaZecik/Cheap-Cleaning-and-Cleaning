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

        if (initialState != null) {
            this.setInitialState(initialState);
        }
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
        if (newState == null) {
            throw new NullPointerException("newState == null");
        }

        dropState();
        putState(newState);
    }

    public void putState(S newState) {
        if (newState == null) {
            throw new NullPointerException("newState == null");
        }

        stateStack.add(newState);
        newState.enter(owner);
    }

    public void dropState() {
        if (stateStack.size == 0) {
            throw new IllegalStateException("stateStack is empty. Cannot drop a state");
        }

        stateStack.pop().exit(owner);
    }

    @Override
    public boolean revertToPreviousState() {
        if (stateStack.size == 0) {
            throw new IllegalStateException("stateStack is empty. Cannot revert to previous state");
        }

        dropState();
        return true;
    }

    @Override
    public void setInitialState(S state) {
        if (state == null) {
            throw new NullPointerException("state == null");
        }

        if (stateStack == null) {
            stateStack = new Array<>();
        }

        clearStack();
        putState(state);
    }

    @Override
    public S getCurrentState() {
        return stateStack == null ? null : stateStack.size == 0 ? globalState : stateStack.peek();
    }

    @Override
    public S getGlobalState() {
        return globalState;
    }

    @Override
    public boolean isInState(S state) {
        return stateStack != null && Objects.equals(state, getCurrentState());
    }

    protected void clearStack() {
        while (stateStack.size != 0) {
            dropState();
        }
    }
}
