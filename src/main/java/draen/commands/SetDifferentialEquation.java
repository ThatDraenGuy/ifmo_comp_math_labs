package draen.commands;

import draen.context.CommandsContext;
import draen.data.math.nonlinear.Storage;

public class SetDifferentialEquation extends BaseCommand {
    public SetDifferentialEquation() {
        super(new CommandData("differential", 'd', "Sets differential equation",
                ArgsType.INT, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        int num = args.getNumInt();
        if (num >= Storage.differentialEquations.length || num < 0)
            return new ExecutionResult(false, "Invalid index");
        context.getConfig().setDifferentialEquation(Storage.differentialEquations[num]);
        return new ExecutionResult(true, "Differential equation set to: "
                + Storage.differentialEquations[num]);
    }
}
