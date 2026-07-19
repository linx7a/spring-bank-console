package linx7a.command;

import linx7a.model.User;
import linx7a.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllUsersCommand implements OperationCommand {
    private final UserService userService;

    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        System.out.println("Список всех пользователей:");
        List<User> allUsers = userService.getAllUsers();
        allUsers.stream().forEach(System.out::println);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }
}
