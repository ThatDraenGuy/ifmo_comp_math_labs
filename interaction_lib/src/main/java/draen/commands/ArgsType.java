package draen.commands;

public enum ArgsType {
    NONE,
    INT,
    DOUBLE,
    STRING,
    FILE,
    RANGE;


    public String display() {
        return switch (this) {
            case NONE -> "";
            case INT -> "<int>";
            case DOUBLE -> "<double>";
            case STRING -> "<string>";
            case FILE -> "<file name>";
            case RANGE -> "<min number> <max number>";
        };
    }
}
