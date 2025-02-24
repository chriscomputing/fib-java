package fib.java;

import java.lang.management.ThreadMXBean;
import java.math.BigInteger;
import java.util.Map;

import fib.java.calculator.BaseCalculator;
import fib.java.scheduler.BaseScheduler;

public class Engine {

    ThreadMXBean bean;

    public Engine() {
        ThreadMXBean bean = java.lang.management.ManagementFactory.getThreadMXBean();

        if (!bean.isCurrentThreadCpuTimeSupported()) {
            bean.setThreadCpuTimeEnabled(true);
        }
        this.bean = bean;
    }

    public double singleRun(BaseCalculator calculator, long n) {
        long start = this.bean.getCurrentThreadCpuTime();
        BigInteger result = calculator.fibonacci(n);
        long end = this.bean.getCurrentThreadCpuTime();
        System.out.println(result);
        return (end - start) / 1e9;
    }

    /**
     * Run an execution time test using a given calculator and scheduler
     * 
     * @param calculator Class that actually calculated a fibonacci number, must
     *                   implement BaseCalculator
     * @param scheduler  Class responsible for scheduling which fibonacci number to
     *                   calculate next, must
     *                   implement BaseScheduler
     * @param n          The initial fibonacci number to start from
     * @param target     The target time to aim for
     * @return A RunResult object containing all runs needed to converge on an
     *         conclusion
     */
    public RunResult run(BaseCalculator calculator, BaseScheduler scheduler, long n, double target) {
        System.out.println("Starting run");
        Map<Long, Double> times = new java.util.HashMap<Long, Double>();
        double time = this.singleRun(calculator, n);
        times.put(n, time);

        while (true) {
            n = scheduler.schedule(time, n, target);
            System.out.println("Doing " + n + "th fib next");
            if (n == -1) {
                break;
            }
            time = this.singleRun(calculator, n);
            // System.out.println(n + "th fib took " + time);
            times.put(n, time);
        }
        System.out.println("last fib took " + time);
        return new RunResult(times);
    }
}
