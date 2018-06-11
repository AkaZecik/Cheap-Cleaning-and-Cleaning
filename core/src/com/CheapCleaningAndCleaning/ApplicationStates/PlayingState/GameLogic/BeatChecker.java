package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BeatChecker extends Thread {
    private long interval;
    private boolean used = false;
    private boolean allow = true;
    private long first = 11 * interval / 12;

    public BeatChecker(double BPM) {
        interval = (long) (30000 / BPM);
    }

    public void run() {
        used = false;
        allow = true;
        try {
            sleep(first);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!isInterrupted()) {
            used = false;
            allow = !allow;
            try {
                sleep(interval);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
    }

    public boolean IsPermitted() {
        if (used) return false;
        used = true;
        return allow;
    }

    public void render(Batch batch){
        batch.begin();
        BitmapFont font = new BitmapFont();
        font.draw(batch, String.valueOf(allow),400,400);
        batch.end();
    }

    public void countFirstBeat() {
        long start = System.nanoTime();
        while (!used) {
            ;
        }
        long end = System.nanoTime();
        first = end - start + interval / 2;
        first = first % (2 * interval);
    }
}
