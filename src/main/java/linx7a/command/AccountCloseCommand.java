package linx7a.command;

import linx7a.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountCloseCommand implements OperationCommand{
    private final AccountService accountService;

    public AccountCloseCommand(AccountService accountService) {
        this.accountService = accountService;
    }
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public void execute() {
        System.out.println("Введите id счета для закрытия:");
        int idAccount = scanner.nextInt();
        accountService.accountClose(idAccount);
        System.out.println("Счет с id " + idAccount + " успешно закрыт");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}
