package draen.math.nonlinear.singular;

import draen.data.math.common.Interval;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.singular.NonLinearSolution;
import draen.exceptions.AlgebraException;

public interface NonlinearEquationMethod {
    NonLinearSolution solve(NonLinearEquation equation, Interval interval, double precision) throws AlgebraException;
}
