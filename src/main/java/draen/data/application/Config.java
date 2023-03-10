package draen.data.application;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {
    public final static double DEFAULT_PRECISION = 0.01;
    private double precision = DEFAULT_PRECISION;
}
