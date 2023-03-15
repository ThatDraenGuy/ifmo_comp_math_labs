package draen.math.nonlinear.singular;

import draen.data.math.nonlinear.singular.Interval;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.singular.NonLinearSolution;
import draen.exceptions.AlgebraException;

import java.time.Duration;
import java.time.Instant;

public class TangentMethod implements NonlinearEquationMethod {
    public static long MAX_STEP_AMOUNT = 100000;
    @Override
    public NonLinearSolution solve(NonLinearEquation equation,
                                   Interval interval,
                                   double precision) throws AlgebraException {
        Instant start = Instant.now();

        double x = interval.getB();

        int stepAmount = 0;
        while (true) {
            double newX;
            newX = iterate(equation, x, precision);
            double diff = Math.abs(newX - x);
            x = newX;
            stepAmount++;
            if (diff < precision) break;
            if (stepAmount >= MAX_STEP_AMOUNT) throw new AlgebraException("Too many iterations!");
        }

        return new NonLinearSolution(x, stepAmount, Duration.between(start, Instant.now()), "Tangent method");
    }

    private double iterate(NonLinearEquation equation, double x, double precision) throws AlgebraException {
        return x - equation.apply(x) / equation.applyDifferential(x, precision / 100);
    }
}
