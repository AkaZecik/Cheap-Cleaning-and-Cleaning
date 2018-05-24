public class IsBeat extends Thread {
    private long interval;
    private boolean allow=true;

    IsBeat(double BPM){
        interval= (long) (30000/BPM);
    }

    public void run() {
        try {
            sleep(interval/2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true) {
            allow=!allow;
            try {
                sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean IsPermitted(){
        return allow;
    }
}
