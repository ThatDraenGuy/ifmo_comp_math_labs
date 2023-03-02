package draen.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommandRequest {
    private final String name;
    private final CommandArgs args;
}
