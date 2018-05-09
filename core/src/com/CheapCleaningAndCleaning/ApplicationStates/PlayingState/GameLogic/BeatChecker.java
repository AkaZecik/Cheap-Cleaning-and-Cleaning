package com.CheapCleaningAndCleaning.ApplicationStates.PlayingState.GameLogic;

public class BeatChecker extends Thread {
    private long interval;
    private boolean used = false;
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
            used = false;
            allow = !allow;
            try {
                sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean IsPermitted() {
        if (used) return false;
        used = true;
        return allow;
    }
}
