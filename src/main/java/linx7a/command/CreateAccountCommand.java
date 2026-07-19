package linx7a.command;

import linx7a.model.Account;
import linx7a.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateAccountCommand implements OperationCommand{
    private final AccountService accountService;
    public CreateAccountCommand(AccountService accountService) {
        this.accountService = accountService;
    }
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("Введите id пользователя, для которого хотите создать аккаунт:");
        int userId = scanner.nextInt();
        Account account = accountService.createAccount(userId);
        System.out.println("Новый счет с id " + account.getId() + " создан");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}
