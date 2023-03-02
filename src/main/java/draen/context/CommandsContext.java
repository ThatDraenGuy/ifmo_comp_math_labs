package draen.context;

import draen.commands.CommandsStorage;
import draen.data.Config;
import draen.data.application.Progress;
import draen.input.IOManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommandsContext implements CommonContext<CommandsContext> {
    private final CommandsStorage<CommandsContext> commandsStorage;
    private final IOManager ioManager;
    private final Progress progress;
    private final Config config;
    private final String[] args;
}
