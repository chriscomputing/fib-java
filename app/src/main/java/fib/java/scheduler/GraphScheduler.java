package fib.java.scheduler;

public class GraphScheduler implements BaseScheduler {
    private final int STEPSIZE = 1;
    private final double THRESHOLD = 0.0;

    public long schedule(double time, long previous, double target) {
        if (time < target - this.THRESHOLD) {
            return previous + this.STEPSIZE;
        }
        return -1;
    }
}
