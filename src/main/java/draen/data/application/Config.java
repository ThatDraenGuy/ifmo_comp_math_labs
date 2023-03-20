package draen.data.application;

import draen.data.math.integral.IntegralFunction;
import draen.data.math.nonlinear.Storage;
import draen.data.math.common.Interval;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.system.NonLinearEquationSystem;
import draen.math.integral.IntegralMethod;
import draen.math.integral.SimpsonMethod;
import draen.math.nonlinear.singular.ChordMethod;
import draen.math.nonlinear.singular.NonlinearEquationMethod;
import draen.math.nonlinear.singular.TangentMethod;
import draen.math.nonlinear.system.NewtonMethod;
import draen.math.nonlinear.system.NonLinearSystemMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {
    public final static double DEFAULT_PRECISION = 0.01;
    public final static int DEFAULT_INTEGRAL_STEP_NUM = 100;
    public final static Interval DEFAULT_INTERVAL = new Interval(0, 1);
    public final static Scenario DEFAULT_SCENARIO = Scenario.NONLINEAR_EQUATION;

    private Scenario scenario = DEFAULT_SCENARIO;
    private Interval solutionInterval = DEFAULT_INTERVAL;
    private double precision = DEFAULT_PRECISION;
    private NonlinearEquationMethod nonlinearEquationMethod1 = new ChordMethod();
    private NonlinearEquationMethod nonlinearEquationMethod2 = new TangentMethod();
    private NonLinearSystemMethod nonLinearSystemMethod = new NewtonMethod();
    private IntegralMethod integralMethod = new SimpsonMethod();

    private NonLinearEquation nonLinearEquation = Storage.equations[0];
    private NonLinearEquationSystem nonLinearEquationSystem = Storage.systems[0];
    private int integralStepNum = DEFAULT_INTEGRAL_STEP_NUM;
    private IntegralFunction integralFunction = Storage.integralFunctions[0];

    public String display() {
        return "precision: " + precision + "\n" +
                "scenario: " + scenario + "\n" +
                switch (scenario) {
                    case NONLINEAR_EQUATION ->
                            "solution interval: " + solutionInterval + "\n" +
                            "selected equation: " + nonLinearEquation;
                    case NONLINEAR_SYSTEM -> "selected system: " + nonLinearEquationSystem;
                    case INTEGRAL ->
                            "integral interval: " + solutionInterval + "\n" +
                            "number of steps: " + integralStepNum + "\n" +
                            "selected function: " + integralFunction;
                };
    }
}
