package Algoritmes;

/**
 *
 * @author remcoruijsenaars
 */
public class StopWatch {

    private long startTime;
    private long elapsedTime;

    private boolean isRunning;

    public StopWatch() {
        reset();
    }

    public void reset() {
        elapsedTime = 0;
        isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            startTime = System.currentTimeMillis();
        }
    }

    public void stop() {
        if (isRunning) {
            isRunning = false;
            long stopTime = System.currentTimeMillis();
            elapsedTime = elapsedTime + stopTime - startTime;
        }

    }

    public long getElapsedTime() {
        if (isRunning) {
            long endTime = System.currentTimeMillis();
            return elapsedTime + endTime - startTime;
        } else {
            return elapsedTime;

        }
    }

}
