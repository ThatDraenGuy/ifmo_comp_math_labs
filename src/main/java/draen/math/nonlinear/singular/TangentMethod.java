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
        double oldX = Math.abs(x) + precision*2;

        long stepAmount = 0;
        while (Math.abs(x - oldX) >= precision) {
            oldX = x;
            x = iterate(equation, x, precision);
            stepAmount++;
        }

        return new NonLinearSolution(x, stepAmount, Duration.between(start, Instant.now()), "Tangent method");
    }

    private double iterate(NonLinearEquation equation, double x, double precision) throws AlgebraException {
        return x - equation.apply(x) / equation.applyDifferential(x, precision / 100);
    }
}
