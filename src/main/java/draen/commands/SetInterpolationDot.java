package draen.commands;

import draen.context.CommandsContext;

public class SetInterpolationDot extends BaseCommand {
    public SetInterpolationDot() {
        super(new CommandData("dot", 'D', "Sets interpolation dot",
                ArgsType.DOUBLE, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        context.getConfig().setInterpolationDot(args.getNumDouble());
        return new ExecutionResult(true, "Successfully set dot to: " + args.getNumDouble());
    }
}
