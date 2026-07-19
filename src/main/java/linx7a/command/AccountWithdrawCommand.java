package linx7a.command;

import linx7a.model.Account;
import linx7a.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountWithdrawCommand implements OperationCommand{
    private final AccountService accountService;

    public AccountWithdrawCommand(AccountService accountService) {
        this.accountService = accountService;
    }
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("Введите id счета для снятия средств:");
        int accountId = scanner.nextInt();
        System.out.println("Введите сумму для снятия:");
        double moneyAmount = scanner.nextDouble();
        Account account = accountService.withdrawAccount(accountId, moneyAmount);

        System.out.println("Сумма " +  moneyAmount + " успешно списана со счета " + accountId +
                ". Текущий баланс: " + account.getMoneyAmount());

    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }
}
