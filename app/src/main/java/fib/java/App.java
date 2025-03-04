package fib.java;

import fib.java.calculator.BaseCalculator;
import fib.java.calculator.FastSquaringCalculator;
import fib.java.scheduler.BaseScheduler;

public class App {
    public static void main(String[] args) {
        // BaseCalculator calculator = new fib.java.calculator.RecursiveCalculator();
        // BaseCalculator calculator = new fib.java.calculator.DynamicCalculator();
        BaseCalculator calculator = new FastSquaringCalculator();
        // BaseScheduler scheduler = new fib.java.scheduler.BinaryScheduler(0.3);
        // BaseScheduler scheduler = new fib.java.scheduler.GraphScheduler();
        BaseScheduler scheduler = new fib.java.scheduler.RatioScheduler(.002);
        Engine engine = new Engine();
        // System.out.println(engine.run(calculator, scheduler, 306000, 1));
        System.out.println(engine.singleRun(calculator, 2743397));
    }
}
