package draen;

import draen.commands.CommandsManager;
import draen.commands.CommandsStorage;
import draen.commands.Help;
import draen.commands.Precision;
import draen.context.CommandsContext;
import draen.context.ControllerContext;
import draen.controllers.ControllerChain;
import draen.controllers.GreetingsController;
import draen.controllers.InteractionController;
import draen.controllers.OptionsController;
import draen.data.application.Config;
import draen.data.application.Progress;
import draen.input.ConsoleManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CommandsStorage<CommandsContext> commandsStorage = new CommandsStorage<>();
        commandsStorage.addCommands(
                new Help(),
                new Precision()
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
                new GreetingsController(),
                new OptionsController<>(),
                new InteractionController<>()
        ));

        controllerChain.begin();
    }
}