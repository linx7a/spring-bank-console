package linx7a.service;

import linx7a.model.Account;
import linx7a.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final List<Account> accounts = new ArrayList<>();
    private final UserService userService;



    public AccountService(UserService userService) {
        this.userService = userService;
    }
    private int idCounter = 0;
    public Account createAccount(int userId) {
        User user = userService.findUserById(userId);
        idCounter++;
        Account account = new Account(idCounter, user.getId(), 3);
        accounts.add(account);
        return account;
    }


}
