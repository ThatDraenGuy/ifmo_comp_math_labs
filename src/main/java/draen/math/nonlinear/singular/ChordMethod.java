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

        double result;
        int stepAmount = 0;
        while (true) {
            result = iterate(equation, interval);
            if (equation.apply(interval.getA()) * equation.apply(result) > 0)
                interval = new Interval(result, interval.getB());
            else
                interval = new Interval(interval.getA(), result);
            stepAmount++;
            if (Math.abs(equation.apply(result)) < precision) break;
            if (stepAmount >= MAX_STEP_AMOUNT) throw new AlgebraException("Too many iterations!");
        }

        return new NonLinearSolution(result, stepAmount, Duration.between(start, Instant.now()),
                "Chord method");
    }

    private double iterate(NonLinearEquation equation, Interval interval) throws AlgebraException {
        double a = interval.getA();
        double b = interval.getB();
        double valueA = equation.apply(a);
        double valueB = equation.apply(b);
        return a - valueA / ( valueB - valueA ) * ( b - a );
    }
}
