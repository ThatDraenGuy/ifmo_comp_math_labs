package draen.data.math.nonlinear.singular;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
public class NonLinearSolution {
    private final double result;
    private final long stepAmount;
    private final Duration duration;
}
