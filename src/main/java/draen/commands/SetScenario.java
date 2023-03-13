package draen.commands;

import draen.context.CommandsContext;
import draen.data.application.Scenario;
import draen.exceptions.ScenarioException;

public class SetScenario extends BaseCommand {
    public SetScenario() {
        super(new CommandData("scenario", 's', "Selects scenario",
                ArgsType.STRING, CommandType.BOTH));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        String scenarioKey = args.getString();
        try {
            Scenario scenario = Scenario.getFromKey(scenarioKey);
            context.getConfig().setScenario(scenario);
            return new ExecutionResult(true, "Successfully set scenario to " + scenarioKey);
        } catch (ScenarioException e) {
            return new ExecutionResult(false, "Unknown scenario!");
        }
    }
}
