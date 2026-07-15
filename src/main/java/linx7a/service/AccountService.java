package linx7a.service;

import linx7a.config.AccountProperties;
import linx7a.model.Account;
import linx7a.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private Account findAccountById(int id) {
        return accounts.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Счет с id " + id + " не найден"));
    }

    public Account depositAccount(int id, double moneyAmount) {
        Account account = findAccountById(id);
        account.deposit(moneyAmount);
        return account;
    }

    public Account withdrawAccount(int id, double moneyAmount) {
        Account account = findAccountById(id);
        account.withdraw(moneyAmount);
        return account;
    }

    public void accountTransfer(int fromAccountId, int toAccountId, double moneyAmount) {
        Account accountFrom = findAccountById(fromAccountId);

        Account accountTo = findAccountById(toAccountId);

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

    public void accountClose(int id) {
        Account account = findAccountById(id);

        int userId = account.getUserId();

        List<Account> allAccounts = accounts.stream()
                .filter(a -> a.getUserId() == userId)
                .collect(Collectors.toList());

        if (allAccounts.size() == 1) {
            throw new IllegalArgumentException("Невозможно закрыть единственный счет пользователя.");
        }

        Account firstAccount = allAccounts.get(0);
        firstAccount.deposit(account.getMoneyAmount());

        accounts.remove(account);
    }

}
