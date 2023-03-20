package draen.math.integral;

import draen.data.math.common.Interval;
import draen.data.math.integral.IntegralFunction;

import java.util.function.BiFunction;

public interface RemovableDiscontinuityStrategy {
    void handle(IntegralFunction function, Interval interval, double dot,
                BiFunction<IntegralFunction, Interval, Double> calculateFunction);
    void handleEdge(IntegralFunction function, double dot);
}
