package fib.java.calculator;

import java.math.BigInteger;

public class DynamicCalculator implements BaseCalculator {

    @Override
    public BigInteger fibonacci(long n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        BigInteger tmp = BigInteger.ZERO;
        for (long i = 0; i < n; i++) {
            // while (n-- > 0) { // Actually no measurable speed difference to for loop
            tmp = a.add(b);
            b = a;
            a = tmp;
        }
        return a;

    }

}
