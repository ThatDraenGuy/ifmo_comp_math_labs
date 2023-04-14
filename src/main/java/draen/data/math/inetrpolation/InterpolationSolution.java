package draen.data.math.inetrpolation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.util.List;

@Getter
@AllArgsConstructor
public class InterpolationSolution {
    private final List<Double> x;
    private final List<Double> y;
    private final Duration duration;
}
