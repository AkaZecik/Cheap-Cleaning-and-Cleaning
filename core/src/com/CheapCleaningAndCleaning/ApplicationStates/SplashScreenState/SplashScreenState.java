package com.CheapCleaningAndCleaning.ApplicationStates.SplashScreenState;

import com.CheapCleaningAndCleaning.ApplicationStates.AbstractApplicationState;
import com.CheapCleaningAndCleaning.ApplicationStates.MainMenuState.MainMenuState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreenState extends AbstractApplicationState {
    private long entered;
    private Texture texture;
    private TextureRegion textureRegion;
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
        textureRegion = new TextureRegion(texture, 150, 0, 800, 700);
        image = new Image(textureRegion);
        image.setFillParent(true);
        stage.addActor(image);
    }

    @Override
    public void update(Game entity) {
        super.update(entity);

        if (System.nanoTime() > entered + 3_000_000_000L) {
            nextState = MainMenuState.getInstance();
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
    }

    private static class InstanceHolder {
        static SplashScreenState instance = new SplashScreenState();
    }
}
