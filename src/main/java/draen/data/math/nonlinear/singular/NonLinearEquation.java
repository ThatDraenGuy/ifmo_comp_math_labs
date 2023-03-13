package draen.data.math.nonlinear.singular;

import draen.exceptions.AlgebraException;
import draen.math.linear.Matrix;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class NonLinearEquation {
    private final int variablesNum;
    private final Function<Matrix, Double> function;
    private final String stringRepresentation;


    public double apply(Matrix variablesRow) throws AlgebraException {
        if (variablesRow.width() != 1 || variablesRow.height() != variablesNum) throw new AlgebraException("Size mismatch!");
        return function.apply(variablesRow);
    }

    public double apply(double value) throws AlgebraException {
        return this.apply(new Matrix(new double[][]{{value}}));
    }


    public double applyDifferential(Matrix variablesRow, int derivativeNum, double step) throws AlgebraException {
        Matrix increasedVariables = new Matrix(variablesRow.width(), variablesRow.height(),
                (i, j) -> i == derivativeNum ? variablesRow.getData()[i][j] + step : variablesRow.getData()[i][j]
        );
        return ( apply(increasedVariables) - apply(variablesRow) ) / step;
    }

    public double applyDifferential(double value, double step) throws AlgebraException {
        return this.applyDifferential(new Matrix(new double[][]{{value}}), 0, step);
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
