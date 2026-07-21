package linx7a.command;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class OperationsConsoleListener implements Runnable{
    private final Map<ConsoleOperationType, OperationCommand> commandMap = new HashMap<>();

    public OperationsConsoleListener(List <OperationCommand> commands) {
        commands.forEach(command -> commandMap.put(command.getOperationType(), command));
    }

    @Override
    public void run() {

    }
}
