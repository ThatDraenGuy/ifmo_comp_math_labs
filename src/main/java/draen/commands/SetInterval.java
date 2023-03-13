package draen.commands;

import draen.context.CommandsContext;
import draen.data.Range;
import draen.data.math.nonlinear.singular.Interval;

public class SetInterval extends BaseCommand {
    public SetInterval() {
        super(new CommandData("interval", 'i', "Sets interval",
                ArgsType.RANGE, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        Range range = args.getRange();
        Interval interval = new Interval(range.getMin(), range.getMax());
        context.getConfig().setSolutionInterval(interval);
        return new ExecutionResult(true, "Solution interval updated");
    }
}
