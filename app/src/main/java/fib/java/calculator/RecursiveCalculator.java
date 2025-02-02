package fib.java.calculator;

import java.math.BigInteger;

public class RecursiveCalculator implements BaseCalculator {
    public RecursiveCalculator() {
    }

    @Override
    public BigInteger fibonacci(long n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        } else {
            return fibonacci(n - 1).add(fibonacci(n - 2));
        }
    }
}
