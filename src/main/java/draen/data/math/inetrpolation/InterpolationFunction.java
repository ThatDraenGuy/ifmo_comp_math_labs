package draen.data.math.inetrpolation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InterpolationFunction {
    private final int dotNum;
    private final List<Double> x;
    private final List<Double> y;
    private final String stringRepresentation;

    private String representList(List<Double> list) {
        StringBuilder builder = new StringBuilder();
        for (double num : list) {
            builder.append(num).append(", ");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return stringRepresentation + "\n" +
                "dots: \n" +
                "x: " + representList(x) + "\n" +
                "y: " + representList(y) + "\n";
    }
}
