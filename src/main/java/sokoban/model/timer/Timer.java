package sokoban.model.timer;

public class Timer {
    private long startTime;
    private long timeElapsedBeforePause;
    private boolean running;

    public void start() {
        if (!this.running) {
            this.running = true;
            this.startTime = System.currentTimeMillis();
        }
    }

    public void stop() {
        if (this.running) {
            this.running = false;
            this.timeElapsedBeforePause += System.currentTimeMillis() - this.startTime;
        }
    }

    public void reset() {
        this.running = false;
        this.timeElapsedBeforePause = 0;
    }

    public int getElapsedTime() {
        if (this.running) {
            return (int)((System.currentTimeMillis() - this.startTime + this.timeElapsedBeforePause) / 1000);
        }
        return (int)this.timeElapsedBeforePause / 1000;
    }
}