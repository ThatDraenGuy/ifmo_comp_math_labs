package draen.data.math.integral;

import draen.data.math.common.SingularFunction;
import draen.data.math.common.Interval;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@AllArgsConstructor
public class IntegralFunction implements SingularFunction {
    private final Function<Double, Double> function;
    private final Function<Double, Double> integralFunction;
    private final Function<Double, Double> forthDifferential;

    private final double[] removableDiscontinuities;
    private final Interval[] essentialDiscontinuities;
    private final String stringRepresentation;

    public double apply(double num) {
        return function.apply(num);
    }

    public double applyIntegral(double num) {
        return integralFunction.apply(num);
    }

    public double applyForthDifferential(double num) {
        return forthDifferential.apply(num);
    }

    public boolean doesIntervalContainAnEssentialDiscontinuity(Interval interval) {
        for (Interval discontinuity : essentialDiscontinuities) {
            if (discontinuity.getA() < interval.getB() && discontinuity.getB() > interval.getA()) return true;
        }
        return false;
    }

    public Double[] getRemovableDiscontinuities(Interval interval) {
        List<Double> discontinuities = new ArrayList<>();
        for (double num : removableDiscontinuities) {
            if (isDotInInterval(num, interval)) discontinuities.add(num);
        }
        return discontinuities.toArray(new Double[0]);
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
