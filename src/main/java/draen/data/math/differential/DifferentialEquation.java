package draen.data.math.differential;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiFunction;

@Getter
@AllArgsConstructor
public class DifferentialEquation {
    private final BiFunction<Double, Double, Double> function;
    private final BiFunction<Double, Double, Double> actualSolution;
    private final BiFunction<Double, Double, Double> constantExpression;
    private final String stringRepresentation;

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
