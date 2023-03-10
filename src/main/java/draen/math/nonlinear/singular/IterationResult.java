package draen.math.nonlinear.singular;

import draen.data.Range;
import draen.data.math.nonlinear.singular.Interval;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IterationResult {
    private final Interval interval;
    private final double c;
    private final double fOfC;
}
