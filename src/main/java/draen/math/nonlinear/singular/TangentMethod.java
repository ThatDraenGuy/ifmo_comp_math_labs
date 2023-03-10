package draen.math.nonlinear.singular;

import draen.data.math.nonlinear.singular.Interval;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.singular.NonLinearSolution;
import draen.exceptions.AlgebraException;

import java.time.Duration;
import java.time.Instant;

public class TangentMethod implements NonlinearEquationMethod {
    @Override
    public NonLinearSolution solve(NonLinearEquation equation,
                                   Interval interval,
                                   double precision) throws AlgebraException {
        Instant start = Instant.now();

        double x = interval.getB();
        double oldX = Math.abs(x) + precision;

        long stepAmount = 0;
        while (Math.abs(x - oldX) >= precision) {
            oldX = x;
            x = iterate(equation, x);
            stepAmount++;
        }

        return new NonLinearSolution(x, stepAmount, Duration.between(start, Instant.now()));
    }

    private double iterate(NonLinearEquation equation, double x) throws AlgebraException {
        return x - equation.apply(x) / equation.applyDifferential(x);
    }
}
