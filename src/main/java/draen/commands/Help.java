package draen.commands;

import draen.context.CommandsContext;

public class Help extends BaseCommand {
    public Help() {
        super(new CommandData("help", 'h', "Displays all commands and their descriptions",
                ArgsType.NONE, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        context.getCommandsStorage().getCommands().forEach(
                cmd -> context.getIoManager().println(displayCmd(cmd))
        );
        return new ExecutionResult(true, "All commands displayed.");
    }

    private String displayCmd(Command<CommandsContext> cmd) {
        CommandData data = cmd.getData();
        if (data.getCommandType().equals(CommandType.NONE)) return "";
        return data.getName() +
                " (-" + data.getKey() + ")" +
                data.getArgsType().display() +
                data.getCommandType().display() + ": " +
                data.getDescription() + "\n";
    }
}
