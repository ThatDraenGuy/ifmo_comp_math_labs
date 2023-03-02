package draen.commands;

import draen.context.CommonContext;

public interface Command<T extends CommonContext<T>> {
    ExecutionResult execute(CommandArgs args, T context);
    CommandData getData();
}
