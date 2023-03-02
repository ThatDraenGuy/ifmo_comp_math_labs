package draen.commands;

import draen.context.CommandsContext;
import draen.format.Formatter;

public class Precision extends BaseCommand {
    public Precision() {
        super(new CommandData("precision", 'p', "Sets desired precision",
                ArgsType.DOUBLE, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        Formatter.setPrecision(args.getNumDouble());
        context.getConfig().setPrecision(args.getNumDouble());
        return new ExecutionResult(true, "Precision set to " + Formatter.formatWithPrecision(args.getNumDouble()));
    }
}
