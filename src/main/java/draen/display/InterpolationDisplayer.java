package draen.display;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonConfig;
import draen.data.application.Config;
import draen.data.math.inetrpolation.InterpolationFunction;
import draen.data.math.inetrpolation.InterpolationSolution;
import draen.input.IOManager;


public class InterpolationDisplayer {

    public void display(InterpolationFunction function, InterpolationSolution solution, Config config,
                        IOManager ioManager) {
        //To lazy to configure any smarter
        Plot plot = Plot.create(PythonConfig.pythonBinPathConfig("/usr/bin/python3"));
        plot.plot()
                .add(function.getX(), function.getY())
                .add(solution.getX(), solution.getY(), "o");
        try {
            plot.show();
        } catch (Exception e) {
            ioManager.displayError("Couldn't display plot: " + e.getMessage());
        }

    }
}
