package draen.data.math.integral;

import draen.data.math.common.SingularFunction;
import draen.data.math.common.Interval;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.function.Function;


@AllArgsConstructor
public class IntegralFunction implements SingularFunction {
    private final Function<Double, Double> function;

    private final double[] removableDiscontinuities;
    private final Interval[] essentialDiscontinuities;
    private final String stringRepresentation;

    public double apply(double num) {
        return function.apply(num);
    }

    public boolean doesIntervalContainAnEssentialDiscontinuity(Interval interval) {
        for (Interval discontinuity : essentialDiscontinuities) {
            if (discontinuity.getA() < interval.getB() && discontinuity.getB() > interval.getA()) return true;
        }
        return false;
    }

    public Optional<Double> getRemovableDiscontinuity(Interval interval) {
        for (double dot : removableDiscontinuities) {
            if (isDotInInterval(dot, interval)) return Optional.of(dot);
        }
        return Optional.empty();
    }

    public boolean isARemovableDiscontinuity(double dot) {
        for (double num : removableDiscontinuities) {
            if (dot == num) return true;
        }
        return false;
    }

    private boolean isDotInInterval(double dot, Interval interval) {
        return dot > interval.getA() && dot < interval.getB();
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
