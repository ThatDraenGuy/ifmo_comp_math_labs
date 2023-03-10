package draen.data.math.nonlinear.system;

import draen.math.linear.Matrix;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
public class SystemSolution {
    private final Matrix result;
    private final long stepAmount;
    private final Duration duration;
}
