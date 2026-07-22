package linx7a.command;

import org.springframework.stereotype.Component;

@Component
public class ExitCommand implements OperationCommand {
    @Override
    public void execute() {
        System.out.println("До свидания!");
        System.exit(0);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.EXIT;
    }
}
