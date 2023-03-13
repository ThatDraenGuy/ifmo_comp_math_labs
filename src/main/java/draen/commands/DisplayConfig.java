package draen.commands;


import draen.context.CommandsContext;

public class DisplayConfig extends BaseCommand {
    public DisplayConfig() {
        super(new CommandData("config", 'C', "Displays current config",
                ArgsType.NONE, CommandType.INTERACT_ONLY));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        context.getIoManager().println(context.getConfig().display());
        return new ExecutionResult(true, "Config displayed");
    }
}
