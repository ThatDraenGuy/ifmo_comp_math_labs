package draen.commands;

import draen.context.CommandsContext;
import draen.data.math.nonlinear.Storage;

public class SetIntegralFunction extends BaseCommand {
    public SetIntegralFunction() {
        super(new CommandData("integral_func", 'f', "Sets integral function",
                ArgsType.INT, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        int num = args.getNumInt();
        if (num >= Storage.integralFunctions.length || num < 0)
            return new ExecutionResult(false, "Invalid index");
        context.getConfig().setIntegralFunction(Storage.integralFunctions[num]);
        return new ExecutionResult(true, "Integral function set to: " + Storage.integralFunctions[num]);
    }
}
