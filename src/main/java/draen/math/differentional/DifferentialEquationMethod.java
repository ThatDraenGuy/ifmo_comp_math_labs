package draen.math.differentional;

import draen.data.math.common.Interval;
import draen.data.math.differential.DifferentialEquation;
import draen.data.math.differential.DifferentialSolution;
import draen.exceptions.AlgebraException;

public interface DifferentialEquationMethod {

    DifferentialSolution solve(DifferentialEquation equation, Interval interval, double starterCondition,
                               int steps) throws AlgebraException;
}
