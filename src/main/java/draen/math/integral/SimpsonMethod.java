package draen.math.integral;

import draen.data.math.integral.IntegralFunction;
import draen.data.math.integral.IntegralSolution;
import draen.data.math.common.Interval;
import draen.exceptions.AlgebraException;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class SimpsonMethod implements IntegralMethod {
    private final double EPSILON = 1e-8;
    @Override
    public IntegralSolution solve(IntegralFunction function, Interval interval, int stepNum) throws AlgebraException {
        Instant start = Instant.now();

        if (function.doesIntervalContainAnEssentialDiscontinuity(interval))
            throw new AlgebraException("Essential discontinuity detected!");

        double a = interval.getA();
        double b = interval.getB();
        double h = ( b - a ) / stepNum;

        if (stepNum % 2 != 0) throw new AlgebraException("Need even amount of intervals!");

        double sum = 0;
        double maxForthDifferential = 0;
        for (int i = 0; i < stepNum - 1; i = i+2) {
            sum += calculateInterval(function, new Interval(a + i*h, a + (i+2)*h));
            double forthDifferential = Math.abs(function.applyForthDifferential(a + i*h));
            if (forthDifferential > maxForthDifferential) maxForthDifferential = forthDifferential;
        }
        sum = h / 3 * sum;

        double actualPrecision = Math.abs(function.applyIntegral(b) - function.applyIntegral(a) - sum );
        double estimatePrecision = (b - a) / 2880 * Math.pow(h, 4) * maxForthDifferential + EPSILON;

        return new IntegralSolution(sum, actualPrecision, estimatePrecision, Duration.between(start, Instant.now()));
    }

    private double calculateInterval(IntegralFunction function, Interval interval) {
        Double[] discontinuities = function.getRemovableDiscontinuities(interval);
        if (discontinuities.length == 0) return getSumElement(function, interval, 1.0);
        Interval[] intervals = new Interval[discontinuities.length+1];
        intervals[0] = new Interval(interval.getA(), discontinuities[0]);

        for (int i = 1; i < discontinuities.length; i++) {
            intervals[i] = new Interval(discontinuities[i-1], discontinuities[i]);
        }
        intervals[discontinuities.length] = new Interval(discontinuities[discontinuities.length-1], interval.getB());

        return Arrays.stream(intervals)
                .map(i -> getSumElement(function, i, 1.0 / intervals.length))
                .reduce(Double::sum).orElse(0.0);
    }

    private double getSumElement(IntegralFunction function, Interval interval, double divider) {
        double a = interval.getA();
        double b = interval.getB();

        if (function.isARemovableDiscontinuity(a)) a = a + EPSILON;
        if (function.isARemovableDiscontinuity(b)) b = b - EPSILON;

        return divider * (function.apply(a) + 4 * function.apply(( a + b ) / 2) + function.apply(b));
    }
}
