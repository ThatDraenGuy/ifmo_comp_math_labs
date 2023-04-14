package draen.math.interpolation;

import draen.data.math.inetrpolation.InterpolationFunction;
import draen.data.math.inetrpolation.InterpolationSolution;
import draen.exceptions.AlgebraException;

import java.util.List;

public interface InterpolationMethod {
    InterpolationSolution interpolate(InterpolationFunction function, List<Double> x) throws AlgebraException;
}
