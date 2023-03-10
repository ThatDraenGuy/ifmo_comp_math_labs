package draen.data.math.linear;

import draen.math.linear.Matrix;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Equation {
    private final Matrix a;
    private final Matrix b;

    public String display() {
        return "A:\n" + a.display() +"\nB:\n" + b.display();
    }
}
