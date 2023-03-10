package draen.data.math.linear;

import draen.format.Formatter;
import draen.math.linear.Matrix;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
public class Solution {
    private final Matrix result;
    private final double estimatePrecision;
    private final Matrix actualPrecision;
    private final long stepAmount;
    private final Duration duration;

    public String display() {
        return "Solution: \n" +
                "- duration: " + duration.toNanos() / 1000 +"μs\n" +
                "- number of iterations: " + stepAmount +"\n" +
                "- X: \n" + result.displayWithPrecision() +
                "- estimate precision: " + Formatter.formatExact(estimatePrecision) + "\n" +
                "- actual precision: \n" + actualPrecision.displayExact();
    }
}
