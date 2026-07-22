package linx7a.command;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class OperationsConsoleListener implements Runnable {
    private final Map<ConsoleOperationType, OperationCommand> commandMap = new HashMap<>();

    public OperationsConsoleListener(List<OperationCommand> commands) {
        commands.forEach(command -> commandMap.put(command.getOperationType(), command));
    }

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            System.out.println("""
                    Пожалуйста, выберите тип операции:
                    - ACCOUNT_CREATE (Создать счет)
                    - SHOW_ALL_USERS (Показать всех пользователей)
                    - ACCOUNT_CLOSE (Закрыть счет)
                    - ACCOUNT_WITHDRAW (Снять средства)
                    - ACCOUNT_DEPOSIT (Пополнить счет)
                    - ACCOUNT_TRANSFER (Перевести средства)
                    - USER_CREATE (Создать пользователя)
                    """);
            String input = scanner.nextLine();
            try {
                ConsoleOperationType type = ConsoleOperationType.valueOf(input);
                OperationCommand command = commandMap.get(type);
                command.execute();
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
