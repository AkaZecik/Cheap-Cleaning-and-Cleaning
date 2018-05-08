package com.CheapCleaningAndCleaning.State;

public interface StateMachine<E, S extends State<E>> {
    void update();
    void changeState(S newState);
    boolean revertToPreviousState();
    void setInitialState(S state);
    S getCurrentState();
    S getGlobalState();
    boolean isInState(S state);
}
