package com.CheapCleaningAndCleaning;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStatesManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CheapCleaningAndCleaning extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ApplicationStatesManager asm;
    private long time = 0;
    private long currentSystemTime;
    private long accumulator = 0;

    @Override
    public void create() {
        asm = new ApplicationStatesManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        batch = new SpriteBatch();
        currentSystemTime = System.nanoTime();
    }

    @Override
    public void render() {
        long newTime = System.nanoTime();
        long frameTime = newTime - currentSystemTime;
        currentSystemTime = newTime;
        if (frameTime > 250_000_000) {
            frameTime = 250_000_000;
        }
        accumulator += frameTime;
        long deltaTime = 10_000_000;
        while (accumulator >= deltaTime) {
            //physics
            asm.update();

            accumulator -= deltaTime;
            time += deltaTime;
        }
        //render
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        asm.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
