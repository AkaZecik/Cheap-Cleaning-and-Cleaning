package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic;

public class BeatChecker extends Thread {
    private long interval;
    private boolean allow = true;

    public BeatChecker(double BPM) {
        interval = (long) (30000 / BPM);
    }

    public void run() {
        try {
            sleep(interval / 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!isInterrupted()) {
            allow = !allow;
            try {
                sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean IsPermitted() {
        return allow;
    }
}
