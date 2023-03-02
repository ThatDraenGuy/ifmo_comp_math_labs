package draen.context;

import draen.commands.CommandsManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ControllerContext<T extends CommonContext<T>> {
    private final T common;
    private final CommandsManager<T> commandsManager;
}
