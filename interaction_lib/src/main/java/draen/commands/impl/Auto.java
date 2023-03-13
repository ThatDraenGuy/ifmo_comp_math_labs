package draen.commands.impl;

import draen.commands.*;
import draen.context.CommonContext;

public class Auto<T extends CommonContext<T>> extends AbstractCommand<T> {
    public Auto() {
        super(new CommandData("auto", 'a',
                "Skips interaction mode and goes straight for calculation",
                ArgsType.NONE, CommandType.OPTION_ONLY));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, T context) {
        context.getProgress().setAuto(true);
        return new ExecutionResult(true, "Starting calculation...");
    }
}