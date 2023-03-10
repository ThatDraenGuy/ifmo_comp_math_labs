package draen.data.math.nonlinear.singular;

import draen.exceptions.AlgebraException;
import draen.math.linear.Matrix;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class NonLinearEquation {
    private final int variablesNum;
    private final Function<Matrix, Double> function;


    public double apply(Matrix variablesRow) throws AlgebraException {
        if (variablesRow.width() != 1 || variablesRow.height() != variablesNum) throw new AlgebraException("Size mismatch!");
        return function.apply(variablesRow);
    }

    public double apply(double value) throws AlgebraException {
        return this.apply(new Matrix(new double[][]{{value}}));
    }


    public double applyDifferential(Matrix variablesRow, int derivativeNum) {

    }

    public double applyDifferential(double value) {
        return this.applyDifferential(new Matrix(new double[][]{{value}}), 0);
    }
}
