package draen.controllers;

import draen.context.CommandsContext;
import draen.context.ControllerContext;

public class GreetingsController implements Controller<CommandsContext> {
    @Override
    public void handle(ControllerContext<CommandsContext> ctx) {
        ctx.getCommon().getIoManager().println(
                "TODO"
        );
    }
}
