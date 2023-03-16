package draen.data.math.nonlinear.singular;

import draen.format.Formatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Getter
@AllArgsConstructor
public class NonLinearSolution {
    private final double result;
    private final long stepAmount;
    private final Duration duration;

    private final String methodName;

    public String display() {
        return "-method: " + methodName + "\n" +
                "-duration: " + duration.toNanos() + " nanoseconds\n" +
                "-stepAmount: " + stepAmount +"\n" +
                "-result: " + Formatter.formatWithPrecision(result);
    }
}
