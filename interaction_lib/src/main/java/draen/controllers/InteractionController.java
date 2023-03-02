package draen.controllers;

import draen.commands.CommandArgs;
import draen.commands.CommandData;
import draen.commands.CommandRequest;
import draen.commands.ExecutionResult;
import draen.context.CommonContext;
import draen.context.ControllerContext;
import draen.data.application.Progress;
import draen.exceptions.ArgsParseException;
import draen.exceptions.OptionsException;
import draen.input.IOManager;

import java.util.Arrays;
import java.util.Iterator;

public class InteractionController<T extends CommonContext<T>> implements Controller<T> {
    @Override
    public void handle(ControllerContext<T> ctx) {
        Progress progress = ctx.getCommon().getProgress();
        IOManager ioManager = ctx.getCommon().getIoManager();

        if (progress.isAuto()) return;

        while (! progress.isStart()) {
            try {
                String input = ioManager.readLine();
                CommandRequest request = parseInput(ctx, input);
                ExecutionResult executionResult = ctx.getCommandsManager().execute(request);
                handleExecutionResult(ioManager, executionResult);
            } catch (Exception e) {
                ioManager.displayError(e);
            }

        }
    }


    private CommandRequest parseInput(ControllerContext<T> ctx, String input) throws OptionsException, ArgsParseException {
        String[] strings = input.split(" ");
        String name = strings[0];

        CommandData data = ctx.getCommon().getCommandsStorage().getDataByName(name);
        if (data == null) throw new OptionsException("Unknown command!");

        Iterator<String> iterator = Arrays.stream(strings).skip(1).iterator();
        CommandArgs args = CommandArgs.parseArgs(iterator, data.getArgsType());
        return new CommandRequest(name, args);
    }

    private void handleExecutionResult(IOManager ioManager, ExecutionResult executionResult) {
        if (executionResult.isSuccess()) {
            ioManager.println(executionResult.getMessage());
        } else {
            ioManager.displayError(executionResult.getMessage());
        }
    }
}
