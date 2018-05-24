package com.CheapCleaningAndCleaning.State;

public interface State<E> {
    void enter(E entity);
    void update(E entity);
    void exit(E entity);
}
