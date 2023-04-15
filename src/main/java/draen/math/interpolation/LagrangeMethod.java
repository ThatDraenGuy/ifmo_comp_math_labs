package draen.math.interpolation;

import draen.data.math.inetrpolation.InterpolationFunction;
import draen.data.math.inetrpolation.InterpolationSolution;
import draen.exceptions.AlgebraException;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class LagrangeMethod implements InterpolationMethod {

    @Override
    public InterpolationSolution interpolate(InterpolationFunction function, List<Double> dots) throws AlgebraException {
        Instant start = Instant.now();

        for (double dot : dots) {
            if (function.getX().get(0) > dot || function.getX().get(function.getX().size()-1) < dot)
                throw new AlgebraException("Point not in range!");
        }

        List<Double> result = new ArrayList<>();
        List<Double> precisions = new ArrayList<>();
        for (double dot : dots) {
            double res = interpolateByLagrange(function.getX(), function.getY(), dot);
            result.add(res);
            precisions.add(Math.abs( res - function.getActualFunction().apply(dot) ));
        }

        return new InterpolationSolution(dots, result, precisions, getApproximatedFunction(function.getX(),
                function.getY()), Duration.between(start, Instant.now()));
    }

    private double interpolateByLagrange(List<Double> xAxis, List<Double> yAxis, double x) {
        return getApproximatedFunction(xAxis, yAxis).apply(x);
    }

    private Function<Double, Double> getApproximatedFunction(List<Double> xAxis, List<Double> yAxis) {
        return (x) -> IntStream.range(0, yAxis.size())
                .mapToDouble(i ->
                        yAxis.get(i) * getMultiplier(i, xAxis).apply(x)
                )
                .reduce(Double::sum).orElse(0);
    }

    private Function<Double, Double> getMultiplier(int i, List<Double> xAxis) {
        double xi = xAxis.get(i);
        return (x) -> IntStream.range(0, xAxis.size())
                .filter(j -> j != i)
                .mapToDouble(xAxis::get)
                .map(num -> (x - num) / (xi - num))
                .reduce((a, b) -> a*b).orElse(0);
    }
}
