package linx7a.service;

import linx7a.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    @Test
    void createUser_shouldCreateUserWithGivenLogin() {
        UserService userService = new UserService();
        User user = userService.createUser("vasyan_bankrot");
        assertEquals("vasyan_bankrot", user.getLogin());
    }

    @Test
    void createUser_shouldThrowException_whenLoginAlreadyExists() {
        UserService userService = new UserService();
        User user = userService.createUser("director_shaurmy");
        assertThrows(IllegalArgumentException.class, () -> userService.createUser("director_shaurmy"));
    }

    @Test
    void findUserById_shouldFindUserWithGivenId() {
        UserService userService = new UserService();
        User user1 = userService.createUser("tetya_klava_invest");
        User user2 = userService.createUser("zolotoy_karas");
        User foundUser1 = userService.findUserById(1);
        User foundUser2 = userService.findUserById(2);
        assertEquals("tetya_klava_invest", foundUser1.getLogin());
        assertEquals("zolotoy_karas", foundUser2.getLogin());
    }

    @Test
    void findUserById_shouldThrowException_whenIdNotExists() {
        UserService userService = new UserService();
        User user = userService.createUser("tolya_million");
        assertThrows(IllegalArgumentException.class, () -> userService.findUserById(3));
    }

    @Test
    void getAllUsers_shouldReturnCorrectNumberOfUsers() {
        UserService userService = new UserService();
        User user1 = userService.createUser("anfisa_broker");
        User user2 = userService.createUser("petya_bez_deneg");
        User user3 = userService.createUser("grustniy_buhgalter");
        assertEquals(3, userService.getAllUsers().size());
    }
}
