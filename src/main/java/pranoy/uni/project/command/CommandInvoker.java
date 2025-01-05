package pranoy.uni.project.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pranoy 21587070
 * <p></p>
 * Invoker class that is used to execute commands
 */
public class CommandInvoker {
    private final List<Command> commandList = new ArrayList<>();

    public void addCommand(Command command) {
        if (!commandList.contains(command)) {
            commandList.add(command);
        }
    }

    public void removeCommand(Command command) {
        commandList.remove(command);
    }

    public void invoke() {
        for (Command command : commandList) {
            command.execute();
        }

        commandList.clear();
    }
}
