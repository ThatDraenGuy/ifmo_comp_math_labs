package draen.data.math.common;

import draen.exceptions.AlgebraException;

public interface SingularFunction {
    double apply(double num) throws AlgebraException;
}
