package draen.commands;

import draen.context.CommandsContext;

public abstract class BaseCommand extends AbstractCommand<CommandsContext> {
    protected BaseCommand(CommandData data) {
        super(data);
    }
}
