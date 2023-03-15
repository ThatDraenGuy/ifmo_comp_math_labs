package draen.math.nonlinear.singular;

import draen.data.math.nonlinear.singular.Interval;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.singular.NonLinearSolution;
import draen.exceptions.AlgebraException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;

public class ChordMethod implements NonlinearEquationMethod {
    public static long MAX_STEP_AMOUNT = 100000;
    @Override
    public NonLinearSolution solve(NonLinearEquation equation,
                                   Interval interval,
                                   double precision) throws AlgebraException {
        Instant start = Instant.now();

        IterationResult iterationResult = new IterationResult(interval, interval.getA(), 0);

        int stepAmount = 0;
        while (true) {
            iterationResult = iterate(equation, iterationResult.getInterval());
            stepAmount++;
            if (Math.abs(iterationResult.getDiff()) < precision) break;
            if (stepAmount >= MAX_STEP_AMOUNT) throw new AlgebraException("Too many iterations!");
        }

        return new NonLinearSolution(iterationResult.getC(), stepAmount, Duration.between(start, Instant.now()), "Chord method");
    }

    private IterationResult iterate(NonLinearEquation equation, Interval interval) throws AlgebraException {
        double a = interval.getA();
        double b = interval.getB();
        double valueA = equation.apply(a);
        double valueB = equation.apply(b);
        double c = a - valueA / ( valueB - valueA ) * ( b - a );
        double valueC = equation.apply(c);
        if (valueA * valueC > 0) a = c;
        else b = c;
        return new IterationResult(new Interval(a, b), c, valueC);
    }

    @Getter
    @AllArgsConstructor
    private static class IterationResult {
        private final Interval interval;
        private final double c;
        private final double diff;
    }
}
