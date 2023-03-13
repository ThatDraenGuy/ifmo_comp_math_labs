package draen.data.math.nonlinear.system;

import draen.format.Formatter;
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

    public String display() {
        return "-duration: " + duration.getNano() / 1000 + " milliseconds\n" +
                "-stepAmount: " + stepAmount +"\n" +
                "-result:\n" + result.displayCustom(
                (i, j) -> "x"+(i+1)+": ",
                Formatter::formatWithPrecision
        );
    }
}
