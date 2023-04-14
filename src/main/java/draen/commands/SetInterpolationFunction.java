package draen.commands;

import draen.context.CommandsContext;
import draen.data.math.nonlinear.Storage;

public class SetInterpolationFunction extends BaseCommand {
    public SetInterpolationFunction() {
        super(new CommandData("interpolation_func", 'f', "Sets interpolation function",
                ArgsType.INT, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        int num = args.getNumInt();
        if (num >= Storage.interpolationFunctions.length || num < 0)
            return new ExecutionResult(false, "Invalid index");
        context.getConfig().setInterpolationFunction(Storage.interpolationFunctions[num]);
        return new ExecutionResult(true, "Integral function set to: " + Storage.interpolationFunctions[num]);
    }
}
