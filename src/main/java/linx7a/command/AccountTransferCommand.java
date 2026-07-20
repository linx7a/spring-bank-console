package linx7a.command;

import linx7a.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountTransferCommand implements OperationCommand{
    private final AccountService accountService;

    public AccountTransferCommand(AccountService accountService) {
        this.accountService = accountService;
    }
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public void execute() {
        System.out.println("Введите id счета-отправителя:");
        int idFrom = scanner.nextInt();

        System.out.println("Введите id счета-получателя:");
        int idTo = scanner.nextInt();

        System.out.println("Введите сумму перевода:");
        double moneyAmount = scanner.nextDouble();

        accountService.accountTransfer(idFrom, idTo, moneyAmount);
        System.out.println("Сумма " + moneyAmount + " успешно переведена со счета " + idFrom + " на счет " + idTo);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}
