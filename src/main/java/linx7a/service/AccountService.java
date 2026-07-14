package linx7a.service;

import linx7a.config.AccountProperties;
import linx7a.model.Account;
import linx7a.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final List<Account> accounts = new ArrayList<>();
    private final UserService userService;
    private final AccountProperties accountProperties;

    public AccountService(UserService userService, AccountProperties accountProperties) {
        this.userService = userService;
        this.accountProperties = accountProperties;
    }


    private int idCounter = 0;

    public Account createAccount(int userId) {
        User user = userService.findUserById(userId);
        idCounter++;
        Account account = new Account(idCounter, user.getId(), accountProperties.getDefaultAmount());
        accounts.add(account);
        return account;
    }

    public Account depositAccount(int id, double moneyAmount) {
        Account account = accounts.stream().
                filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Счет с id " + id + " не найден"));
        account.deposit(moneyAmount);
        return account;
    }

    public Account withdrawAccount(int id, double moneyAmount) {
        Account account = accounts.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Счет с id " + id + " не найден"));

        account.withdraw(moneyAmount);

        return account;
    }

    public void accountTransfer(int fromAccountId, int toAccountId, double moneyAmount) {
        Account accountFrom = accounts.stream()
                .filter(a -> a.getId() == fromAccountId)
                .findFirst().
                orElseThrow(() -> new IllegalArgumentException("Счет отправителя " + fromAccountId + " не найден"));

        Account accountTo = accounts.stream().
                filter(a -> a.getId() == toAccountId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Счет получателя " + toAccountId + " не найден"));

        boolean sameUser = accountFrom.getUserId() == accountTo.getUserId();
        double depositMoneyAmount = 0;

        if (!sameUser) {
            depositMoneyAmount = moneyAmount * (1 - accountProperties.getTransferCommission());
        } else {
            depositMoneyAmount = moneyAmount;
        }

        accountFrom.withdraw(moneyAmount);
        accountTo.deposit(depositMoneyAmount);
    }

}
