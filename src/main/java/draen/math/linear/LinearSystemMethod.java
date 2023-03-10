package draen.math.linear;

import draen.data.math.linear.Equation;
import draen.data.math.linear.Solution;
import draen.exceptions.AlgebraException;

public interface LinearSystemMethod {
    Solution solve(Equation equation, double precision) throws AlgebraException;
}
