package draen.commands;

import draen.context.CommonContext;
import draen.exceptions.ArgsTypeException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommandsManager<T extends CommonContext<T>> {
    private final T context;


    public ExecutionResult execute(CommandRequest request) throws ArgsTypeException {
        Command<T> command = context.getCommandsStorage().getByName(request.getName());
        if (! command.getData().getArgsType().equals(request.getArgs().getType())) {
            throw new ArgsTypeException("Args type mismatch");
        }
        return command.execute(request.getArgs(), context);
    }
}
