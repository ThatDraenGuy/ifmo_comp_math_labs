package draen.math.nonlinear.system;

import draen.data.math.nonlinear.system.NonLinearEquationSystem;
import draen.data.math.nonlinear.system.SystemSolution;
import draen.exceptions.AlgebraException;

public interface NonLinearSystemMethod {
    SystemSolution solve(NonLinearEquationSystem system, double precision) throws AlgebraException;
}
