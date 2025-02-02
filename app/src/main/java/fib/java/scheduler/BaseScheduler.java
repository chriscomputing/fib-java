package fib.java.scheduler;

public interface BaseScheduler {

    /**
     * Returns the next fibonacci number that should be calculated according to the
     * previous iteration
     * 
     * @param time     Execution time taken by the previous calculation
     * @param previous Which fibonacci number was calculated in the previous
     *                 iteration
     * @param target   The target time the scheduler aims to need
     * @return Next fibonacci number to calculate. -1 if target is considered
     *         reached.
     *         -1 indicates that this run is over.
     */
    public long schedule(double time, long previous, double target);

}
