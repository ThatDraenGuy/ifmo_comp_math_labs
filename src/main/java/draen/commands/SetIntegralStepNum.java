package draen.commands;

import draen.context.CommandsContext;

public class SetIntegralStepNum extends BaseCommand {
    public SetIntegralStepNum() {
        super(new CommandData("integral_steps", 'I', "Sets integral step num",
                ArgsType.INT, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        int num = args.getNumInt();
        context.getConfig().setIntegralStepNum(num);
        return new ExecutionResult(true, "Successfully updated integral step num");
    }
}
