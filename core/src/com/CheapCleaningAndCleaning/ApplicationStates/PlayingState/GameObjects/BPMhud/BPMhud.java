package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.BPMhud;

import com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameObjects.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BPMhud extends GameObject {
    long interval;
    long begin = System.nanoTime();
    private Texture texture1 = new Texture(Gdx.files.internal("image/circle.png"));
    private Texture texture2 = new Texture(Gdx.files.internal("image/BeatSign.png"));

    public BPMhud(double BPM) {
        interval = (long) (60000 / BPM);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        long timeFromStart = System.nanoTime() - begin;
        timeFromStart /= 1e6;

        float numerator = (float) interval - timeFromStart % interval;
        float denominator = (float) interval;
        float scale = numerator / denominator;

        batch.draw(texture2, -64 * scale, -136 + (-64 * scale), 128 * scale, 128 * scale);
        batch.draw(texture1, -64, -200);
    }
}
