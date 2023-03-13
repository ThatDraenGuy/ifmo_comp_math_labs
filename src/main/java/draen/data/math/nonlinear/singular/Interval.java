package draen.data.math.nonlinear.singular;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Interval {
    private final double a;
    private final double b;

    @Override
    public String toString() {
        return "from " + a + " to " + b;
    }
}
