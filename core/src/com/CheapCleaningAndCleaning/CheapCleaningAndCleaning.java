package com.CheapCleaningAndCleaning;

import com.CheapCleaningAndCleaning.ApplicationStates.ApplicationStatesManager;
import com.badlogic.gdx.ApplicationAdapter;

public class CheapCleaningAndCleaning extends ApplicationAdapter {
    private ApplicationStatesManager asm;
    private long time = 0;
    private long currentSystemTime;
    private long accumulator = 0;

    @Override
    public void create() {
        asm = new ApplicationStatesManager();
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
        asm.renderImage();
    }
}
