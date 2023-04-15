package draen.data.math.nonlinear;


import draen.Main;
import draen.data.math.common.Interval;
import draen.data.math.differential.DifferentialEquation;
import draen.data.math.inetrpolation.InterpolationFunction;
import draen.data.math.integral.IntegralFunction;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.system.NonLinearEquationSystem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Storage {
    public static final NonLinearEquation[] equations = new NonLinearEquation[] {
            new NonLinearEquation(
                    1,
                    matrix -> {
                        double num = matrix.getData()[0][0];
                        return Math.pow(num, 2) - 3*num;
                    },
                    "x^2 - 3*x"),
            new NonLinearEquation(
                    1,
                    matrix -> {
                        double num = matrix.getData()[0][0];
                        return Math.pow(num, 3) + Math.cos(num);
                    },
                    "x^3 + cos(x)"),
            new NonLinearEquation(
                    1,
                    matrix -> {
                        double num = matrix.getData()[0][0];
                        return Math.pow(2, num) - Math.pow(num, 2) * Math.log(num-3) - 15;
                    },
                    "2^x - x^2*ln(x-3) - 15")
    };


    public static final NonLinearEquationSystem[] systems = new NonLinearEquationSystem[] {
            new NonLinearEquationSystem(
                    2,
                    new NonLinearEquation[]{
                            new NonLinearEquation(
                                    2,
                                    matrix -> {
                                        double x1 = matrix.getData()[0][0];
                                        double x2 = matrix.getData()[1][0];
                                        return Math.pow(x1, 3) + Math.cos(x1) + 3*x1 - x2;
                                    },
                                    "x1^3 + cos(x1) + 3x1 - x2"
                            ),
                            new NonLinearEquation(
                                    2,
                                    matrix -> {
                                        double x1 = matrix.getData()[0][0];
                                        double x2 = matrix.getData()[1][0];
                                        return Math.pow(x1, 2) - x1 + 3*x2;
                                    },
                                    "x1^2 - x1 + 3*x2"
                            )
                    }),
            new NonLinearEquationSystem(
                    2,
                    new NonLinearEquation[]{
                            new NonLinearEquation(
                                    2,
                                    matrix -> {
                                        double x1 = matrix.getData()[0][0];
                                        double x2 = matrix.getData()[1][0];
                                        return Math.pow(x1, 2) - x2;
                                    },
                                    "x1^2 - x2"
                            ),
                            new NonLinearEquation(
                                    2,
                                    matrix -> {
                                        double x1 = matrix.getData()[0][0];
                                        double x2 = matrix.getData()[1][0];
                                        return x1 - 1 + Math.pow(x2, 2);
                                    },
                                    "x1 - 1 + x2^2"
                            )
                    })
    };

    public static final IntegralFunction[] integralFunctions = new IntegralFunction[] {
            new IntegralFunction(
                    (x) -> Math.pow(x, 2),
                    (x) -> 1.0/3.0 * Math.pow(x, 3),
                    (x) -> 0.0,
                    new double[]{},
                    new Interval[]{},
                    "x^2"
            ),
            new IntegralFunction(
                    Math::sqrt,
                    (x) -> 2.0/3.0 * Math.pow(x, 1.5),
                    (x) -> -15.0/( 16.0 * Math.pow(x, 3.5) ),
                    new double[]{},
                    new Interval[]{},
                    "sqrt(x)"
            ),
            new IntegralFunction(
                    Math::sin,
                    (x) -> -1*Math.cos(x),
                    Math::sin,
                    new double[]{},
                    new Interval[]{},
                    "sin(x)"
            ),
            new IntegralFunction(
                    (x) -> ( Math.pow(x, 2) + 5*x ) / x,
                    (x) -> 1.0/2.0 * x * (x + 10),
                    (x) -> 0.0,
                    new double[]{0.0},
                    new Interval[]{},
                    "(x^2 + 5x)/x"
            ),
            new IntegralFunction(
                    (x) -> 1/x,
                    Math::log,
                    (x) -> 24.0 / Math.pow(x, 5),
                    new double[]{},
                    new Interval[]{new Interval(0.0, 0.0)},
                    "1/x"
            ),
            new IntegralFunction(
                    (x) -> x * Math.pow(2, x),
                    (x) -> Math.pow(2, x) * ( x * Math.log(2) - 1 ) /( Math.pow( Math.log(2), 2 ) ),
                    (x) -> Math.pow(2, x) * Math.pow( Math.log(2), 3 ) * ( x * Math.log(2) + 4 ),
                    new double[]{-0.1, -0.05, 0.0, 0.05, 0.1},
                    new Interval[]{},
                    "x*2^x"
            )
    };

    public static final InterpolationFunction[] interpolationFunctions = new InterpolationFunction[] {
            new InterpolationFunction(
                    5,
                    List.of(0.0, 1.0, 2.0, 3.0, 4.0, 5.0),
                    (x) -> Math.pow(x, 2),
                    "y = x^2"
            ),
            new InterpolationFunction(
                    3,
                    List.of(-2.0, 0.0, 2.0),
                    Math::exp,
                    "y = e^x"
            ),
            new InterpolationFunction(
                    5,
                    List.of(-2.0, -1.0, 0.0, 1.0, 2.0),
                    Math::exp,
                    "y = e^x"
            ),
            new InterpolationFunction(
                    5,
                    List.of(-4.0, -2.0, 0.0, 2.0, 4.0),
                    Math::sin,
                    "y = sin(x)"
            ),
            new InterpolationFunction(
                    4,
                    List.of(0.1, 0.5, 1.0, 1.5),
                    x -> Math.pow(x, 3) * Math.log(x) + Math.sin(x) / x,
                    "y = x^3*log(x) + sin(x)/x"
            ),
            new InterpolationFunction(
                    15,
                    List.of(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0,
                            1.1, 1.2, 1.3, 1.4, 1.5),
                    x -> Math.pow(x, 3) * Math.log(x) + Math.sin(x) / x,
                    "y = x^3*log(x) + sin(x)/x"
            )
    };

    public static final DifferentialEquation[] differentialEquations = new DifferentialEquation[] {
            new DifferentialEquation(
                    (x, y) -> Math.sin(x),
                    (x, c) -> c - Math.cos(x),
                    (x, y) -> y + Math.cos(x),
                    "dy/dx = sin(x)"
            ),
            new DifferentialEquation(
                    (x, y) -> (x * y) / 2,
                    (x, c) -> c * Math.exp( Math.pow(x, 2) / 4 ),
                    (x, y) -> y / Math.exp( Math.pow(x, 2) / 4 ),
                    "dy/dx = xy/2"
            ),
            new DifferentialEquation(
                    (x, y) -> x + y,
                    (x, c) -> c * Math.exp(x) - x - 1,
                    (x, y) -> (y + x + 1) / Math.exp(x),
                    "dy/dx = x+y"
            )
    };
}
