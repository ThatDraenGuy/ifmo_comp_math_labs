package draen.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommandData {
    private final String name;
    private final char key;
    private final String description;
    private final ArgsType argsType;
    private final CommandType commandType;
}
