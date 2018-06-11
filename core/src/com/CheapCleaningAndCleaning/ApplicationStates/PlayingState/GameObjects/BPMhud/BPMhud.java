package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.BPMhud;

import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic.BeatChecker;
import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BPMhud extends GameObject {
    long interval;
    long first;
    long begin = System.nanoTime();
    BeatChecker beat;

    private Texture texture1 = new Texture(Gdx.files.internal("image/circle2.png"));
    private Texture texture2 = new Texture(Gdx.files.internal("image/BadClick.png"));
    private Texture texture3 = new Texture(Gdx.files.internal("image/GoodClick.png"));

    public BPMhud(BeatChecker b, double BPM) {
        beat = b;
        interval = 2 * b.intervalValue();
        first = b.firstValue();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        long timeFromStart = System.nanoTime() - begin;
        timeFromStart /= 1e6;

        float numerator = (float) first + interval - timeFromStart % interval;
        float denominator = (float) interval;
        float scale = numerator / denominator;

        if (!beat.allowStatus()) batch.draw(texture2, -64 * scale, -136 + (-64 * scale), 128 * scale, 128 * scale);
        else batch.draw(texture3, -64 * scale, -136 + (-64 * scale), 128 * scale, 128 * scale);
        batch.draw(texture1, -64, -200);
    }
}
