package draen.commands;

import draen.context.CommonContext;
import lombok.Getter;

public abstract class AbstractCommand<T extends CommonContext<T>> implements Command<T> {
    @Getter
    private final CommandData data;

    protected AbstractCommand(CommandData data) {
        this.data = data;
    }
}
