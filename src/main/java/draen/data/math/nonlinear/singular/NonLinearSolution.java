package draen.data.math.nonlinear.singular;

import draen.format.Formatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
public class NonLinearSolution {
    private final double result;
    private final long stepAmount;
    private final Duration duration;

    private final String methodName;

    public String display() {
        return "-method: " + methodName + "\n" +
                "-duration: " + duration.getNano() / 1000 + " milliseconds\n" +
                "-stepAmount: " + stepAmount +"\n" +
                "-result: " + Formatter.formatWithPrecision(result);
    }
}
