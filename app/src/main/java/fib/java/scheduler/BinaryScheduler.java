package fib.java.scheduler;

public class BinaryScheduler implements BaseScheduler {

    private double oldTime = -1; // Time needed for the calculation 2 steps ago
    private long oldN = -1; // Which fib was calculated 2 steps ago
    private final double THRESHOLD;

    public BinaryScheduler(double threshold) {
        this.THRESHOLD = threshold;
    }

    public long schedule(double time, long previous, double target) {
        // Check if last run reached target
        if (this.THRESHOLD > Math.abs(time - target)) {
            return -1;
        }
        // Check if this is the first run
        if (this.oldN == -1 || this.oldTime == -1) {
            this.oldN = previous;
            this.oldTime = time;
            if (time > target) {
                return previous / 2;
            } else {
                return previous * 2;
            }
        }

        // Within a run, do the full magic
        long newN = -1;
        if (this.oldTime < target && time > target) { // n is between old and previous
            // Find middle
            newN = (long) (this.oldN + previous) / 2;
        }
        if (this.oldTime > target && time < target) { // n is between old and previous
            newN = (long) (this.oldN + previous) / 2;
        }
        if (this.oldTime < target && time < target) { // n is larger than previous
            newN = previous * 2;
        }
        if (this.oldTime > target && time > target) { // n is smaller than previous
            newN = previous / 2;
        }
        this.oldN = previous;
        this.oldTime = time;
        return newN;

    }
}
