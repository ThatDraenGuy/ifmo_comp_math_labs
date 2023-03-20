package draen.math.integral;

import draen.data.math.integral.IntegralFunction;
import draen.data.math.integral.IntegralSolution;
import draen.data.math.common.Interval;
import draen.exceptions.AlgebraException;

public interface IntegralMethod {
    IntegralSolution solve(IntegralFunction function, Interval interval, int stepNum) throws AlgebraException;
}
