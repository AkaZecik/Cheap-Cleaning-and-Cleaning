package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BeatChecker extends Thread {
    private long interval;
    private boolean moveAlreadyUsed = false;
    private boolean moveAllowed = true;
    private long firstBeatTimeShift = 0;

    public BeatChecker(double BPM) {
        interval = (long) (30000 / BPM);
    }

    public long intervalValue() {
        return interval;
    }

    public long firstValue() {
        return firstBeatTimeShift;
    }

    public void run() {
        moveAlreadyUsed = false;
        moveAllowed = true;

        try {
            sleep(firstBeatTimeShift);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!isInterrupted()) {
            moveAlreadyUsed = false;
            moveAllowed = !moveAllowed;
            try {
                sleep(interval);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
    }

    public boolean isPermitted() {
        if (moveAlreadyUsed) {
            return false;
        }

        moveAlreadyUsed = true;
        return moveAllowed;
    }

    public boolean allowStatus() {
        return moveAllowed;
    }

    public void render(Batch batch) {
        batch.begin();
        BitmapFont font = new BitmapFont();
        font.draw(batch, String.valueOf(moveAllowed), 400, 400);
        batch.end();
    }

    public void countFirstBeat() {
//        long start = System.nanoTime();
//        while (!moveAlreadyUsed) {
//            ;
//        }
//        long end = System.nanoTime();
//        firstBeatTimeShift = end - start + interval / 2;
//        firstBeatTimeShift = firstBeatTimeShift % (2 * interval);
    }
}
