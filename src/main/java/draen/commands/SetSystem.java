package draen.commands;

import draen.context.CommandsContext;
import draen.data.math.nonlinear.Storage;

public class SetSystem extends BaseCommand {
    public SetSystem() {
        super(new CommandData("system", 'S', "Sets system",
                ArgsType.INT, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        int num = args.getNumInt();
        if (num >= Storage.systems.length || num < 0)
            return new ExecutionResult(false, "Invalid index");
        context.getConfig().setNonLinearEquationSystem(Storage.systems[num]);
        return new ExecutionResult(true, "System set to: " + Storage.systems[num]);
    }
}
