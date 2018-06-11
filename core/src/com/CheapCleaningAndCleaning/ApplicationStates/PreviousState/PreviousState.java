package com.CheapCleaningAndCleaning.ApplicationStates.PreviousState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStackStateMachine;
import com.CheapCleaningAndCleaning.CheapCleaningAndCleaning;

public class PreviousState extends AbstractApplicationState {
    private PreviousState(ApplicationStackStateMachine stateMachine) {
        super(stateMachine);
    }

    public static PreviousState getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        public static PreviousState instance = new PreviousState(CheapCleaningAndCleaning.getStateMachine());
    }
}
