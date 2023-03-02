package draen.commands;

public enum CommandType {
    NONE,
    OPTION_ONLY,
    INTERACT_ONLY,
    BOTH;
    public String display() {
        return switch (this) {
            case NONE, BOTH -> "";
            case OPTION_ONLY -> "(option only)";
            case INTERACT_ONLY -> "(only in interaction mode)";
        };
    }
}
