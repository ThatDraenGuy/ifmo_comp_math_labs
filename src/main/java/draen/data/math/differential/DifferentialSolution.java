package draen.data.math.differential;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DifferentialSolution {
    private final double x;
    private final double y;
    private final List<Double> xAxis;
    private final List<Double> yAxis;
    private final double precision;

    @Override
    public String toString() {
        return "solution: (" + x + ", " + y + ")\n" +
                "precision: " + precision;
    }
}
