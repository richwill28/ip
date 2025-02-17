package duke.command;

public enum CommandType {
    DATE,
    DEADLINE,
    DELETE,
    DONE,
    EVENT,
    EXIT,
    FIND,
    HELP,
    INVALID,
    LIST,
    TODO;

    /**
     * Determines the correct command type from the input parameter.
     *
     * @param param the first parameter of the user response
     * @return the command type of the input parameter
     */
    public static CommandType of(String param) {
        for (CommandType commandType : CommandType.values()) {
            if (param.equalsIgnoreCase(commandType.toString())) {
                return commandType;
            }
        }
        return INVALID;
    }
}
