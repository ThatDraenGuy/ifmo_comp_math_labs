package draen.data.math.nonlinear;


import draen.Main;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.system.NonLinearEquationSystem;

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
}
