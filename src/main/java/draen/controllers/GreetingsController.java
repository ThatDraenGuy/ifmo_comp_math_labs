package draen.controllers;

import draen.context.CommandsContext;
import draen.context.ControllerContext;
import draen.input.IOManager;

public class GreetingsController implements Controller<CommandsContext> {
    @Override
    public void handle(ControllerContext<CommandsContext> ctx) {
        IOManager ioManager = ctx.getCommon().getIoManager();
        ioManager.println(
                """
                          _   _             _ _                      \s
                         | \\ | |           | (_)                     \s
                         |  \\| | ___  _ __ | |_ _ __   ___  __ _ _ __\s
                         | . ` |/ _ \\| '_ \\| | | '_ \\ / _ \\/ _` | '__|
                         | |\\  | (_) | | | | | | | | |  __/ (_| | |  \s
                         |_|_\\_|\\___/|_| |_|_|_|_| |_|\\___|\\__,_|_|  \s
                          / ____|     | |                            \s
                         | (___   ___ | |_   _____ _ __              \s
                          \\___ \\ / _ \\| \\ \\ / / _ \\ '__|             \s
                          ____) | (_) | |\\ V /  __/ |                \s
                         |_____/ \\___/|_| \\_/ \\___|_|                \s
                                                                     \s
                         created by ThatDraenGuy
                        """
        );
        if (ctx.getCommon().getProgress().isAuto()) return;
        ioManager.println(
                """
                        Welcome to Matrix Solver (real name TBA)!
                        You are currently in the interaction mode.
                        Type 'help' to see your options.
                        Type 'go' to begin calculation.
                        
                        Current configuration:"""
        );
        ioManager.println(ctx.getCommon().getConfig().display());
    }
}
