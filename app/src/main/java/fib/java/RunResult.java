package fib.java;

import java.util.Map;

public class RunResult {
    private Map<Long, Double> results;

    public RunResult(Map<Long, Double> results) {
        this.results = results;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Double> entry : this.results.entrySet()) {
            sb.append("Fibonacci(");
            sb.append(entry.getKey());
            sb.append("): ");
            sb.append(entry.getValue());
            sb.append("seconds\n");
        }
        return sb.toString();
    }
}
