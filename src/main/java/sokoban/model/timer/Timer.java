package sokoban.model.timer;

/**
 * Simple game timer supporting start, stop and reset.
 */
public class Timer {
    private long startTime;
    private long timeElapsedBeforePause;
    private boolean running;

    /**
     * Starts the timer.
     */
    public void start() {
        if (!this.running) {
            this.running = true;
            this.startTime = System.currentTimeMillis();
        }
    }

    /**
     * Stops the timer and preserves elapsed time.
     */
    public void stop() {
        if (this.running) {
            this.running = false;
            this.timeElapsedBeforePause += System.currentTimeMillis() - this.startTime;
        }
    }

    /**
     * Resets the timer state.
     */
    public void reset() {
        this.running = false;
        this.timeElapsedBeforePause = 0;
    }

    /**
     * @return elapsed time in seconds
     */
    public int getElapsedTime() {
        if (this.running) {
            return (int)((System.currentTimeMillis() - this.startTime + this.timeElapsedBeforePause) / 1000);
        }
        return (int)this.timeElapsedBeforePause / 1000;
    }
}