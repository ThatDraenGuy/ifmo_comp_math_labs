package draen.math;

import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.singular.NonLinearSolution;

public interface NonLinearEquationMethod {
    NonLinearSolution solve(NonLinearEquation equation);
}
