package linx7a.command;

import linx7a.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountDepositCommand implements OperationCommand{
    private final AccountService accountService;

    public AccountDepositCommand(AccountService accountService) {
        this.accountService = accountService;
    }
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("Введите id счета:");
        int accountId = scanner.nextInt();
        System.out.println("Введите сумму пополнения:");
        double moneyAmount = scanner.nextDouble();
        accountService.depositAccount(accountId, moneyAmount);
        System.out.println("Сумма " + moneyAmount + " успешно зачислена на счет " + accountId);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSIT;
    }
}
