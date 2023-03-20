package draen.data.math.integral;

import draen.format.Formatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
public class IntegralSolution {
    private final double result;
    private final double actualPrecision;
    private final double estimatePrecision;
    private final Duration duration;

    public String display() {
        return
                "-result: " + Formatter.formatWithPrecision(result) + '\n' +
                "-duration: " + duration.toNanos() + " nanoseconds\n" +
                "-estimate precision: " + estimatePrecision + '\n' +
                "-actual precision: " + actualPrecision;
    }
}
