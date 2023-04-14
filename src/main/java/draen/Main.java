package draen;

import draen.commands.*;
import draen.commands.impl.Auto;
import draen.commands.impl.Go;
import draen.context.CommandsContext;
import draen.context.ControllerContext;
import draen.controllers.*;
import draen.data.application.Config;
import draen.data.application.Progress;
import draen.input.ConsoleManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CommandsStorage<CommandsContext> commandsStorage = new CommandsStorage<>();
        commandsStorage.addCommands(
                new Help(),
                new DisplayConfig(),
                new DisplayEquations(),
                new Auto<>(),
                new Go<>(),
                new Precision(),
                new SetScenario(),
                new SetInterval(),
                new SetEquation(),
                new SetSystem(),
                new SetIntegralFunction(),
                new SetIntegralStepNum(),
                new SetInterpolationFunction(),
                new SetInterpolationDot()
        );

        CommandsContext context = new CommandsContext(
                commandsStorage,
                new ConsoleManager(),
                new Progress(),
                new Config(),
                args
        );

        CommandsManager<CommandsContext> commandsManager = new CommandsManager<>(context);

        ControllerContext<CommandsContext> controllerContext = new ControllerContext<>(
                context, commandsManager
        );

        ControllerChain<CommandsContext> controllerChain = new ControllerChain<>(controllerContext, List.of(
                new OptionsController<>(),
                new GreetingsController(),
                new InteractionController<>(),
                new CalculationController(),
                new FarewellController()
        ));

        controllerChain.begin();
    }
}