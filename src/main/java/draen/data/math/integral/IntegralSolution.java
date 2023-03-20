package draen.data.math.integral;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
public class IntegralSolution {
    private final double result;
    private final double precision;
    private final Duration duration;

    public String display() {
        return
                "-result: " + result + '\n' +
                "-duration: " + duration.toNanos() + " nanoseconds\n" +
                "-precision: " + precision;
    }
}
