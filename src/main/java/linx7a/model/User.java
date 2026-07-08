package linx7a.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

    private final int id;
    private final String login;
    private final List<Account> accountList = new ArrayList<>();

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<Account> getAccountList() {
        return Collections.unmodifiableList(accountList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", accountList=" + accountList +
                '}';
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }
}
