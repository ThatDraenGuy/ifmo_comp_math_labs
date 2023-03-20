package draen.data.application;

import draen.exceptions.ScenarioException;

public enum Scenario {
    NONLINEAR_EQUATION,
    NONLINEAR_SYSTEM,
    INTEGRAL;

    public static Scenario getFromKey(String key) throws ScenarioException {
        return switch (key) {
            case "nonlinear_equation" -> NONLINEAR_EQUATION;
            case "nonlinear_system" -> NONLINEAR_SYSTEM;
            case "integral" -> INTEGRAL;
            default -> throw new ScenarioException("Unknown scenario!");
        };
    }
}

