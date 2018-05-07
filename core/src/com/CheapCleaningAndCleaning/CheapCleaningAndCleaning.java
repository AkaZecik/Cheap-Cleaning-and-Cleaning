package com.CheapCleaningAndCleaning;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CheapCleaningAndCleaning extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    private long time = 0;
    private long currentSystemTime = System.nanoTime();
    private long accumulator = 0;

    @Override
    public void render() {
        long newTime = System.nanoTime();
        long frameTime = newTime - currentSystemTime;
        currentSystemTime = newTime;
        accumulator += frameTime;
        long deltaTime = 10000000;
        while (accumulator >= deltaTime) {
            //physics
            System.out.println(accumulator);
            accumulator -= deltaTime;
            time += deltaTime;
        }
        //render
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }
}
