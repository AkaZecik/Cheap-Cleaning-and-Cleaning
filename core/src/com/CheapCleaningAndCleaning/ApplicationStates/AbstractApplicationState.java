package com.CheapCleaningAndCleaning.ApplicationStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class AbstractApplicationState implements ApplicationState {
    protected Stage stage;

    @Override
    public void enter(Game entity) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1 ,1 , 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void exit(Game entity) {
        stage.dispose();
    }
}
