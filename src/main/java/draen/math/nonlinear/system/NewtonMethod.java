package draen.math.nonlinear.system;

import draen.data.math.linear.Equation;
import draen.data.math.linear.Solution;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.system.NonLinearEquationSystem;
import draen.data.math.nonlinear.system.SystemSolution;
import draen.exceptions.AlgebraException;
import draen.math.linear.LinearSystemMethod;
import draen.math.linear.Matrix;
import draen.math.linear.SimpleIterationAlgorithm;

import java.time.Duration;
import java.time.Instant;

public class NewtonMethod implements NonLinearSystemMethod {
    public static long MAX_STEP_AMOUNT = 100000;
    private final LinearSystemMethod linearSystemMethod = new SimpleIterationAlgorithm();
    @Override
    public SystemSolution solve(NonLinearEquationSystem system, double precision) throws AlgebraException {
        Instant start = Instant.now();

        Matrix x = new Matrix(1, system.getEquations().length, 1);

        int stepAmount = 0;
        while(true) {
            Matrix newX;
            try {
                newX = iterate(x, system, precision);
            } catch (AlgebraException e) {
                break;
            }
            double diff = newX.add(x.mul(-1)).norm();
            x = newX;
            stepAmount++;
            if (diff < precision) break;
            if (stepAmount >= MAX_STEP_AMOUNT) throw new AlgebraException("Too many iterations!");
        }

        return new SystemSolution(x, stepAmount, Duration.between(start, Instant.now()));
    }


    private Matrix iterate(Matrix x, NonLinearEquationSystem system, double precision) throws AlgebraException {
        Matrix jacobian = getJacobian(system, x, precision);
        Matrix f = system.apply(x).mul(-1);
        Solution linearSolution = linearSystemMethod.solve(new Equation(jacobian, f), precision);
        return x.add(linearSolution.getResult());

    }


    private Matrix getJacobian(NonLinearEquationSystem system, Matrix x, double precision) {
        NonLinearEquation[] equations = system.getEquations();
        int length = equations.length;
        return new Matrix(length, length, (i, j) -> {
            try {
                return equations[i].applyDifferential(x, j, precision / 100);
            } catch (AlgebraException e) {
                return 0.0;
            }
        });
    }

}
