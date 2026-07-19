package linx7a.command;

import linx7a.model.User;
import linx7a.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateUserCommand implements OperationCommand{
    private final UserService userService;

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public void execute() {
        System.out.println("Введите логин для создания пользователя: ");
        String login = scanner.nextLine();
        User user = userService.createUser(login);
        System.out.println("Пользователь создан: " + user);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.USER_CREATE;
    }
}
