package draen.commands;

import draen.context.CommonContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandsStorage<T extends CommonContext<T>> {
    private final Map<String, Command<T>> commandsByName;
    private final Map<Character, Command<T>> commandsByKey;

    public CommandsStorage() {
        commandsByName = new HashMap<>();
        commandsByKey = new HashMap<>();
    }

    @SafeVarargs
    public final void addCommands(Command<T>... commands) {
        for (Command<T> command : commands) {
            this.commandsByName.put(command.getData().getName(), command);
            this.commandsByKey.put(command.getData().getKey(), command);
        }
    }

    public Command<T> getByName(String name) {
        return commandsByName.get(name);
    }
    public Command<T> getByKey(char key) {
        return commandsByKey.get(key);
    }
    public CommandData getDataByName(String name) {
        Command<T> command = commandsByName.get(name);
        if (command == null) return null;
        return command.getData();
    }
    public CommandData getDataByKey(char key) {
        Command<T> command = commandsByKey.get(key);
        if (command == null) return null;
        return command.getData();
    }

    public Collection<Command<T>> getCommands() {
        return commandsByName.values();
    }
}
