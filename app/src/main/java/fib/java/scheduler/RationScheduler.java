package fib.java.scheduler;

public class RationScheduler implements BaseScheduler {

    private final double THRESHOLD;

    public RationScheduler(double threshold) {
        this.THRESHOLD = threshold;
    }

    public long schedule(double time, long previous, double target) {
        if (this.THRESHOLD > Math.abs(time - target))
            return -1;

        double dampener = 1;
        double factor = Math.max(Math.min(target / time, 2.5), 0.5);

        if (factor < 1) {
            dampener = 1.02;
        } else {
            dampener = 0.98;
        }

        return (long) Math.ceil(factor * previous * dampener);
    }
}
