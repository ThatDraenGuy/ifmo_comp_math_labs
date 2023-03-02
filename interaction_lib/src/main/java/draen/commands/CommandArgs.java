package draen.commands;

import draen.data.Range;
import draen.exceptions.ArgsParseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandArgs {
    private final Integer numInt;
    private final Double numDouble;
    private final File file;
    private final Range range;
    private final ArgsType type;

    public static CommandArgs none() {
        return new CommandArgs(null, null, null, null, ArgsType.NONE);
    }

    public static CommandArgs numInt(int num) {
        return new CommandArgs(num, null, null, null, ArgsType.INT);
    }

    public static CommandArgs numDouble(double num) {
        return new CommandArgs(null, num, null, null, ArgsType.DOUBLE);
    }

    public static CommandArgs file(File file) {
        return new CommandArgs(null, null, file, null, ArgsType.FILE);
    }

    public static CommandArgs range(Range range) {
        return new CommandArgs(null, null, null, range, ArgsType.RANGE);
    }

    public static CommandArgs parseArgs(Iterator<String> params, ArgsType argsType) throws ArgsParseException {
        try {
            return switch (argsType) {
                case NONE -> CommandArgs.none();
                case INT -> {
                    String arg = params.next();
                    try {
                        yield CommandArgs.numInt(Integer.parseInt(arg));
                    } catch (NumberFormatException e) {
                        throw new ArgsParseException(
                                "Invalid argument encountered: expected an int, but got a '" + arg + "' instead"
                        );
                    }
                }
                case DOUBLE -> {
                    String arg = params.next();
                    try {
                        yield CommandArgs.numDouble(Double.parseDouble(arg));
                    } catch (NumberFormatException e) {
                        throw new ArgsParseException(
                                "Invalid argument encountered: expected a double, but got a '" + arg + "' instead"
                        );
                    }
                }
                case RANGE -> {
                    String min = params.next();
                    String max = params.next();
                    try {
                        yield CommandArgs.range(new Range(Double.parseDouble(min), Double.parseDouble(max)));
                    } catch (NumberFormatException e) {
                        throw new ArgsParseException(
                                "Invalid argument encountered: expected 2 doubles, but got a '" +
                                        min + " " + max + "' instead"
                        );
                    }

                }
                case FILE -> {
                    String fileName = params.next();
                    File file = new File(fileName);
                    if (!file.canRead()) throw new ArgsParseException("Cannot read file by name '" + fileName + "'");
                    yield CommandArgs.file(file);
                }
            };
        } catch (NoSuchElementException e) {
            throw new ArgsParseException("Ran out of parameters!");
        }
    }
}
