package draen.commands;

import draen.context.CommandsContext;
import draen.data.math.nonlinear.Storage;
import draen.input.IOManager;

public class DisplayEquations extends BaseCommand {
    public DisplayEquations() {
        super(new CommandData("equations", 'e', "Shows available equations and systems",
                ArgsType.NONE, CommandType.INTERACT_ONLY));
    }

    @Override
    public ExecutionResult execute(CommandArgs args, CommandsContext context) {
        IOManager ioManager = context.getIoManager();
        ioManager.println("Nonlinear equations:");
        for (int i = 0; i < Storage.equations.length; i++) {
            ioManager.println(i + ": " + Storage.equations[i].toString());
        }
        ioManager.println("\nNonlinear systems:");
        for (int i = 0; i < Storage.systems.length; i++) {
            ioManager.println(i + ":\n" + Storage.systems[i].toString());
        }
        ioManager.println("\nIntegral functions:");
        for (int i = 0; i < Storage.integralFunctions.length; i++) {
            ioManager.println(i + ": " + Storage.integralFunctions[i].toString());
        }
        ioManager.println("\nInterpolation functions:");
        for (int i = 0; i < Storage.interpolationFunctions.length; i++) {
            ioManager.println(i + ": " + Storage.interpolationFunctions[i].toString());
        }
        return new ExecutionResult(true, "All equations, systems & functions displayed");
    }
}
