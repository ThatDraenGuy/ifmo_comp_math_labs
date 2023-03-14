package draen.commands;

import draen.context.CommandsContext;
import draen.data.math.nonlinear.Storage;

public class SetEquation extends BaseCommand {
    public SetEquation() {
        super(new CommandData("equation", 'E', "Sets equation",
                ArgsType.INT, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        int num = args.getNumInt();
        if (num >= Storage.equations.length || num < 0)
            return new ExecutionResult(false, "Invalid index");
        context.getConfig().setNonLinearEquation(Storage.equations[num]);
        return new ExecutionResult(true, "Equation set to: " + Storage.equations[num]);
    }
}
