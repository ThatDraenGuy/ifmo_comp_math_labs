package draen.commands.impl;

import draen.commands.*;
import draen.context.CommonContext;

public class Go<T extends CommonContext<T>> extends AbstractCommand<T> {
    public Go() {
        super(new CommandData("go", 'g', "Starts calculation",
                ArgsType.NONE, CommandType.INTERACT_ONLY));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, T context) {
        context.getProgress().setStart(true);
        return new ExecutionResult(true, "Starting calculation...");
    }
}
