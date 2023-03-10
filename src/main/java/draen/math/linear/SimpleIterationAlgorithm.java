package draen.math.linear;

import draen.data.math.linear.Equation;
import draen.data.math.linear.Solution;
import draen.exceptions.AlgebraException;

import java.time.Duration;
import java.time.Instant;


public class SimpleIterationAlgorithm implements LinearSystemMethod {
    public static long MAX_STEP_AMOUNT = 100000;

    private Matrix iterate(Matrix a, Matrix b, Matrix x) throws AlgebraException {
        return a.mul(x).add(b);
    }

    @Override
    public Solution solve(Equation equation, double precision) throws AlgebraException {
        Instant startTime = Instant.now();
        Matrix.Extended extendedMatrix = Matrix.Extended.fromEquation(equation);

        if (! extendedMatrix.getBase().isDiagonalDominating()) {
            extendedMatrix = extendedMatrix.getWithDiagonalDominating();
        }

        extendedMatrix = extendedMatrix.prepareForIteration();
        Matrix a = extendedMatrix.getBase();
        Matrix b = extendedMatrix.getResult();
        Matrix x = b.getCopy();

        long stepAmount = getStepAmount(precision, a, b, x);
        if (stepAmount < 1) throw new AlgebraException("Matrix is invalid and cannot be solved using this method");
        if (stepAmount > MAX_STEP_AMOUNT) throw new AlgebraException("Cannot handle such precision");
        double estimatePrecision = getPrecisionEstimate(stepAmount, a, b, x);
        for (long i = 0; i < stepAmount; i++) {
            x = iterate(a, b, x);
        }
        Matrix actualPrecisionMatrix = getActualPrecision(equation, x);
        return new Solution(x, estimatePrecision, actualPrecisionMatrix,
                stepAmount, Duration.between(startTime, Instant.now()));
    }

    private long getStepAmount(double precision, Matrix a, Matrix b, Matrix x) {
        double normA = a.norm();
        double normB = b.norm();
        double normX = x.norm();

        double k = Math.log(precision / ( normX + normB / ( 1 - normA ) ) ) / Math.log(normA);
        return Math.round(k) + 1;
    }

    private double getPrecisionEstimate(long stepAmount, Matrix a, Matrix b, Matrix x) {
        double normA = a.norm();
        double normB = b.norm();
        double normX = x.norm();

        return Math.pow(normA, stepAmount) * ( normX + normB / ( 1 - normA ) );
    }

    private Matrix getActualPrecision(Equation equation, Matrix solution) throws AlgebraException {
        Matrix result = equation.getA().mul(solution);
        return equation.getB().getDifference(result);
    }
}
