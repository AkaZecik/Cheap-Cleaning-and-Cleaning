package com.CheapCleaningAndCleaning.ApplicationStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class AbstractApplicationState implements ApplicationState {
    protected ApplicationStackStateMachine stateMachine;
    protected Stage stage;

    protected AbstractApplicationState(ApplicationStackStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void enter(Game entity) {
        System.out.println("Entering " + this.getClass().getSimpleName() + " at " + System.currentTimeMillis());
        stage = new Stage(new ScreenViewport());
        setInputProcessor();
    }

    @Override
    public void update(Game entity) {
        stage.act();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void exit(Game entity) {
        System.out.println("Leaving " + this.getClass().getSimpleName() + " at " + System.currentTimeMillis());
        stage.dispose();
        stage = null;
    }

    public void setInputProcessor() {
        Gdx.input.setInputProcessor(stage);
    }
}
