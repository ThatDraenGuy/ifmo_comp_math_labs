package draen.data.math.nonlinear.system;

import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.exceptions.AlgebraException;
import draen.math.linear.Matrix;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NonLinearEquationSystem {
    private final int variablesNum;
    private final NonLinearEquation[] equations;


    public Matrix apply(Matrix variablesRow) throws AlgebraException {
        if (variablesRow.width() != 1 || variablesRow.height() != variablesNum) throw new AlgebraException("Size mismatch!");
        return new Matrix(1, equations.length, (i, j) -> {
            try {
                return equations[i].apply(variablesRow);
            } catch (AlgebraException ignored) {
                return 0.0;
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (var equation : equations) {
            builder.append(equation.toString()).append('\n');
        }
        return builder.toString();
    }
}