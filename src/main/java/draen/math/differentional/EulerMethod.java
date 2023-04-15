package draen.math.differentional;
import draen.data.math.common.Interval;
import draen.data.math.differential.DifferentialEquation;
import draen.data.math.differential.DifferentialSolution;
import draen.exceptions.AlgebraException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;


public class EulerMethod implements DifferentialEquationMethod {

    @Override
    public DifferentialSolution solve(DifferentialEquation equation, Interval interval, double starterCondition,
                                      int steps) throws AlgebraException {
        BiFunction<Double, Double,  Double> function = equation.getFunction();

        double a = interval.getA();
        double b = interval.getB();

        double step = (b - a) / steps;

        List<Double> xAxis = new ArrayList<>(steps);
        List<Double> yAxis = new ArrayList<>(steps);

        double x = a;
        double y = starterCondition;
        for (int i = 1; i <= steps; i++) {
            xAxis.add(x);
            yAxis.add(y);
            double newX = x + step;
            y = getNextY(function, x, y, newX);
            x = newX;
        }
        xAxis.add(x);
        yAxis.add(y);

        double c = equation.getConstantExpression().apply(a, starterCondition);
        double precision = Math.abs( y - equation.getActualSolution().apply(x, c));
        return new DifferentialSolution(x, y, xAxis, yAxis, precision);
    }


    private double getNextY(BiFunction<Double, Double, Double> function, double prevX, double prevY,
                            double x) {
        double y = prevY + (x - prevX) * function.apply(prevX, prevY);
        return prevY + (x - prevX) * (function.apply(prevX, prevY) + function.apply(x, y)) / 2;
    }
}
