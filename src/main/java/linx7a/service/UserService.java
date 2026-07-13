package linx7a.service;

import linx7a.model.Account;
import linx7a.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();
    private int idCounter = 0;

    public User createUser(String login) {
        boolean loginExists = users.stream().anyMatch(user -> user.getLogin().equals(login));
        if (loginExists) {
            throw new IllegalArgumentException("Пользователь с логином " + login + " уже существует");
        }

        idCounter++;
        User user = new User(idCounter, login);
        users.add(user);
        return user;
    }

    public User findUserById(int id) {
        User user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с id " + id + " не найден"));
        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }
}
