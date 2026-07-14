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


}
