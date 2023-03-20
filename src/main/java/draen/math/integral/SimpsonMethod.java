package draen.math.integral;

import draen.data.math.integral.IntegralFunction;
import draen.data.math.integral.IntegralSolution;
import draen.data.math.common.Interval;
import draen.exceptions.AlgebraException;

import java.time.Duration;
import java.time.Instant;

public class SimpsonMethod implements IntegralMethod {
    @Override
    public IntegralSolution solve(IntegralFunction function, Interval interval, int stepNum) throws AlgebraException {
        Instant start = Instant.now();

        if (function.doesIntervalContainAnEssentialDiscontinuity(interval))
            throw new AlgebraException("Essential discontinuity detected!");

        double a = interval.getA();
        double b = interval.getB();
        double h = ( interval.getB() - interval.getA() ) / stepNum;

        if (stepNum % 2 != 0) throw new AlgebraException("Need even amount of intervals!");

        double sum = 0;
        for (int i = 0; i < stepNum - 1; i = i+2) {
            sum += calculateInterval(function, new Interval(a + i*h, a + (i+1)*h));
        }
        sum = h / 3 * sum;

        return new IntegralSolution(sum, 0, Duration.between(start, Instant.now()));
    }

    private double calculateInterval(IntegralFunction function, Interval interval) {
        double a = interval.getA();
        double b = interval.getB();

        return function.apply(a) + 4 * function.apply(( a + b ) / 2) + function.apply(b);
    }
}
