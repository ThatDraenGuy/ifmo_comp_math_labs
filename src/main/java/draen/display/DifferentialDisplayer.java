package draen.display;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import draen.data.application.Config;
import draen.data.math.common.Interval;
import draen.data.math.differential.DifferentialEquation;
import draen.data.math.differential.DifferentialSolution;
import draen.input.IOManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DifferentialDisplayer {

    public void display(DifferentialEquation equation, DifferentialSolution solution, Interval interval,
                        double starterCondition, Config config, IOManager ioManager) {
        Plot plot = Plot.create(PythonConfig.pythonBinPathConfig("/usr/bin/python3"));

        double start = interval.getA();
        double end = interval.getB();
        int step = 100;
        List<Double> dots = new ArrayList<>(step);
        for (int i = 0; i < step; i++) {
            dots.add(start + (end - start) / step * (i+1));
        }

        double c = equation.getConstantExpression().apply(interval.getA(), starterCondition);

        plot.plot()
                .add(dots, dots.stream()
                        .map(num -> equation.getActualSolution().apply(num, c))
                        .collect(Collectors.toList())).label("actual function");
        plot.plot()
                .add(solution.getXAxis(), solution.getYAxis())
                .label("steps");
        plot.plot()
                .add(List.of(solution.getX()), List.of(solution.getY()), "o")
                .label("solution");
        plot.legend().loc("upper left");

        try {
            plot.show();
        } catch (Exception e) {
            ioManager.displayError("Couldn't display plot: " + e.getMessage());
        }
    }
}
