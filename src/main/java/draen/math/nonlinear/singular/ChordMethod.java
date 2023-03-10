package draen.math.nonlinear.singular;

import draen.data.Range;
import draen.data.math.nonlinear.singular.Interval;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.singular.NonLinearSolution;
import draen.exceptions.AlgebraException;
import draen.math.linear.Matrix;

import java.time.Duration;
import java.time.Instant;

public class ChordMethod implements NonlinearEquationMethod {
    @Override
    public NonLinearSolution solve(NonLinearEquation equation,
                                   Interval interval,
                                   double precision) throws AlgebraException {
        Instant start = Instant.now();

        IterationResult iterationResult = new IterationResult(interval, interval.getA(), precision+1);

        long stepAmount = 0;
        while ((Math.abs(iterationResult.getFOfC()) >= precision)){
            iterationResult = iterate(equation, iterationResult.getInterval());
            stepAmount++;
        }

        return new NonLinearSolution(iterationResult.getC(), stepAmount, Duration.between(start, Instant.now()));
    }

    private IterationResult iterate(NonLinearEquation equation, Interval interval) throws AlgebraException {
        double a = interval.getA();
        double b = interval.getB();
        double fOfA = equation.apply(a);
        double fOfB = equation.apply(b);
        double c = a - fOfA / ( fOfB - fOfA ) * ( b - a );
        double fOfC = equation.apply(c);
        if (fOfA * fOfC > 0) a = c;
        else b = c;
        return new IterationResult(new Interval(a, b), c, fOfC);
    }
}
