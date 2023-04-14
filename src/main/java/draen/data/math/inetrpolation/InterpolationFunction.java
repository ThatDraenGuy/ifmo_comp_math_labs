package draen.data.math.inetrpolation;

import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class InterpolationFunction {
    private final int dotNum;
    private final List<Double> x;
    private final List<Double> y;
    private final Function<Double, Double> actualFunction;
    private final String stringRepresentation;

    public InterpolationFunction(int dotNum, List<Double> x, Function<Double, Double> actualFunction, String stringRepresentation) {

        this.dotNum = dotNum;
        this.x = x;
        this.actualFunction = actualFunction;
        this.stringRepresentation = stringRepresentation;

        y = x.stream().map(actualFunction).collect(Collectors.toList());
    }

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
