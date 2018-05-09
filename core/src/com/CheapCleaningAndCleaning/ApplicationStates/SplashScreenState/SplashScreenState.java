package com.CheapCleaningAndCleaning.ApplicationStates.SplashScreenState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.PlayingState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

public class SplashScreenState extends AbstractApplicationState {
    private long entered;
    private Texture texture;
    private Image image;

    private SplashScreenState() {

    }

    public static SplashScreenState getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void enter(Game entity) {
        super.enter(entity);
        entered = System.nanoTime();
        texture = new Texture(Gdx.files.internal("image/disclaimer.png"));
        image = new Image(texture);
        stage.addActor(image);
        System.out.println(System.nanoTime()/1_000_000_000);
    }

    @Override
    public void update(Game entity) {
        super.update(entity);

        if (System.nanoTime() > entered + 5_000_000_000L) {
            nextState = PlayingState.getInstance();
            System.out.println(System.nanoTime()/1_000_000_000);
        }
    }

    @Override
    public void exit(Game entity) {
        super.exit(entity);
        texture.dispose();
    }

    @Override
    public void render() {
        super.render();
//        stage.getBatch().draw(texture, 0, 0, stage.getWidth(), stage.getHeight());
    }

    private static class InstanceHolder {
        static SplashScreenState instance = new SplashScreenState();
    }
}
