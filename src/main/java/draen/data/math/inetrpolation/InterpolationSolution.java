package draen.data.math.inetrpolation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public class InterpolationSolution {
    private final List<Double> x;
    private final List<Double> y;
    private final List<Double> precisions;
    private final Function<Double, Double> approximatedFunction;
    private final Duration duration;

    private String representDots() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < x.size(); i++) {
            builder.append("dot: (").append(x.get(i)).append(", ").append(y.get(i)).append("); precision: ")
                    .append(precisions.get(i));
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Solution: \n"
                + "duarion: " + duration.toNanos() + " nanoseconds\n"
                + "dots: \n" + representDots();
    }
}
