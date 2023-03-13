package draen.controllers;

import draen.context.CommandsContext;
import draen.context.ControllerContext;
import draen.data.application.Scenario;
import draen.data.math.nonlinear.singular.Interval;
import draen.data.math.nonlinear.singular.NonLinearEquation;
import draen.data.math.nonlinear.singular.NonLinearSolution;
import draen.data.math.nonlinear.system.NonLinearEquationSystem;
import draen.data.math.nonlinear.system.SystemSolution;
import draen.exceptions.AlgebraException;
import draen.input.IOManager;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalculationController implements Controller<CommandsContext> {
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    @Override
    public void handle(ControllerContext<CommandsContext> ctx) {
        Scenario scenario = ctx.getCommon().getConfig().getScenario();
        switch (scenario) {
            case NONLINEAR_EQUATION -> handleEquation(ctx);
            case NONLINEAR_SYSTEM -> handleSystem(ctx);
        }
    }

    private void handleEquation(ControllerContext<CommandsContext> ctx) {
        IOManager ioManager = ctx.getCommon().getIoManager();
        NonLinearEquation equation = ctx.getCommon().getConfig().getNonLinearEquation();
        Interval interval = ctx.getCommon().getConfig().getSolutionInterval();
        double precision = ctx.getCommon().getConfig().getPrecision();
        try {
            List<Future<NonLinearSolution>> solutions = executorService.invokeAll(List.of(
                () -> ctx.getCommon().getConfig().getNonlinearEquationMethod1().solve(equation, interval, precision),
                () -> ctx.getCommon().getConfig().getNonlinearEquationMethod2().solve(equation, interval, precision)
            ));
            for (var solution : solutions) {
                ioManager.println(solution.get().display());
                ioManager.println("");
            }
        } catch (Exception ignored) {}
        executorService.shutdown();
    }

    private void handleSystem(ControllerContext<CommandsContext> ctx) {
        IOManager ioManager = ctx.getCommon().getIoManager();
        NonLinearEquationSystem system = ctx.getCommon().getConfig().getNonLinearEquationSystem();
        double precision = ctx.getCommon().getConfig().getPrecision();
        try {
            SystemSolution solution = ctx.getCommon().getConfig().getNonLinearSystemMethod().solve(system, precision);
            ioManager.println(solution.display());
        } catch (AlgebraException e) {
            ioManager.displayError(e);
        }
    }
}
