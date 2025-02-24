package fib.java.calculator;

import java.math.BigInteger;

import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;

// Fibonacci(2734078): 1.007862526seconds
public class FastSquaringCalculator implements BaseCalculator {

    BigFraction a = BigFraction.ZERO;
    BigFraction b = BigFraction.ONE;
    BigFraction c = BigFraction.ONE;
    BigFraction d = BigFraction.ONE;

    @Override
    public BigInteger fibonacci(long n) {
        BigFraction[][] identity = { { a, c }, { b, d } };
        FieldMatrix<BigFraction> matrix = new Array2DRowFieldMatrix<>(identity);
        FieldMatrix<BigFraction> result = matrixPower(matrix, n);

        BigInteger exactInteger;
        if (result.getEntry(0, 1).getDenominator().equals(BigInteger.ONE)) {
            exactInteger = result.getEntry(0, 1).getNumerator();
        } else {
            System.out.println("Warning: Loosing precision by converting BigFraction to BigInteger");
            System.out.println("Denominator is " + result.getEntry(0, 1).getDenominatorAsInt());
            throw new ArithmeticException("Denominator is not one, precision loss detected");
        }

        return exactInteger;

    }

    private FieldMatrix<BigFraction> matrixPower(FieldMatrix<BigFraction> matrix, long power) {
        FieldMatrix<BigFraction> result = MatrixUtils.createFieldIdentityMatrix(
                BigFractionField.getInstance(),
                matrix.getRowDimension());
        while (power > 0) {
            if (power % 2 == 1) {
                result = result.multiply(matrix);
            }
            matrix = matrix.multiply(matrix);
            power /= 2;
        }
        return result;
    }
}
