package linx7a.service;


import linx7a.config.AccountProperties;
import linx7a.model.Account;
import linx7a.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    @Test
    void createAccount_shouldCreateAccountWithDefaultBalance() {
        UserService userService = new UserService();
        AccountProperties accountProperties = new AccountProperties(100, 0.02);
        AccountService accountService = new AccountService(userService, accountProperties);

        User user = userService.createUser("zina_millionersha");
        Account account = accountService.createAccount(user.getId());

        assertEquals(100, account.getMoneyAmount());
    }

    @Test
    void depositAccount_shouldDepositMoneyToChosenAccount() {
        UserService userService = new UserService();
        AccountProperties accountProperties = new AccountProperties(100, 0.02);
        AccountService accountService = new AccountService(userService, accountProperties);

        User user = userService.createUser("dyadya_vova_nal");
        Account account = accountService.createAccount(user.getId());
        accountService.depositAccount(account.getId(), 5000);

        assertEquals(5100, account.getMoneyAmount());
    }

    @Test
    void withdrawAccount_ShouldWithdrawMoneyFromChosenAccount() {
        UserService userService = new UserService();
        AccountProperties accountProperties = new AccountProperties(100, 0.02);
        AccountService accountService = new AccountService(userService, accountProperties);

        User user = userService.createUser("tosha_perevodchik");
        Account account = accountService.createAccount(user.getId());
        accountService.withdrawAccount(account.getId(), 10);

        assertEquals(90, account.getMoneyAmount());
        assertThrows(IllegalArgumentException.class, () -> accountService.withdrawAccount(account.getId(),1000));
    }

    @Test
    void transferAccount_ShouldTransferMoneyBetweenAccountsOfOneUser() {
        UserService userService = new UserService();
        AccountProperties accountProperties = new AccountProperties(100, 0.02);
        AccountService accountService = new AccountService(userService, accountProperties);

        User user1 = userService.createUser("garik_bez_kopeyki");
        Account account1 = accountService.createAccount(user1.getId());
        Account account2 = accountService.createAccount(user1.getId());
        accountService.depositAccount(account1.getId(), 7777);
        accountService.accountTransfer(account1.getId(),account2.getId(), 777);

        assertEquals(7100, account1.getMoneyAmount());
        assertEquals(877, account2.getMoneyAmount());

        assertThrows(IllegalArgumentException.class, () -> accountService.accountTransfer(account1.getId(), account2.getId(), 1000000));
    }

    @Test
    void transferAccount_ShouldTransferMoneyBetweenAccountsOfDifferentUsers() {
        UserService userService = new UserService();
        AccountProperties accountProperties = new AccountProperties(100, 0.02);
        AccountService accountService = new AccountService(userService, accountProperties);

        User user1 = userService.createUser("pasha_krypto");
        User user2 = userService.createUser("lyuda_finmonitoring");
        Account account1 = accountService.createAccount(user1.getId());
        Account account2 = accountService.createAccount(user2.getId());

        accountService.depositAccount(account1.getId(), 8356.72);
        accountService.accountTransfer(account1.getId(), account2.getId(), 50);

        assertEquals(8406.72, account1.getMoneyAmount());
        assertEquals(149, account2.getMoneyAmount());

        assertThrows(IllegalArgumentException.class, () -> accountService.accountTransfer(account1.getId(), account2.getId(), 200000));
    }
}
