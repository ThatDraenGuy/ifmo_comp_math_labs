package draen.display;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import draen.data.application.Config;
import draen.data.math.inetrpolation.InterpolationFunction;
import draen.data.math.inetrpolation.InterpolationSolution;
import draen.input.IOManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class InterpolationDisplayer {

    public void display(InterpolationFunction function, InterpolationSolution solution, Config config,
                        IOManager ioManager) {
        //To lazy to configure any smarter
        Plot plot = Plot.create(PythonConfig.pythonBinPathConfig("/usr/bin/python3"));

        double start = function.getX().get(0);
        double end = function.getX().get(function.getX().size()-1);
        int step = 100;
        List<Double> dots = new ArrayList<>(step);
        for (int i = 0; i < step; i++) {
            dots.add(start + (end - start) / step * (i+1));
        }

        plot.plot()
                .add(dots, dots.stream()
                        .map(num -> function.getActualFunction().apply(num))
                        .collect(Collectors.toList())).label("function");
        plot.plot()
                .add(dots, dots.stream()
                        .map(num -> solution.getApproximatedFunction().apply(num))
                        .collect(Collectors.toList())).label("interpolation");
        plot.plot()
                .add(function.getX(), function.getY(), "o").label("initial dots");
        plot.plot()
                .add(solution.getX(), solution.getY(), "o").label("interpolated dots");
        plot.legend().loc("upper left");
        try {
            plot.show();
        } catch (Exception e) {
            ioManager.displayError("Couldn't display plot: " + e.getMessage());
        }

    }
}
